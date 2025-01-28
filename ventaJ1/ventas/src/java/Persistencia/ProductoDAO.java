/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import config.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Producto;

/**
 *
 * @author Bernal
 */
public class ProductoDAO {
    
    
    public boolean insertar(Producto prod) {
        ConexionMySQL instance = ConexionMySQL.getInstance();
        Connection cnn;
        boolean resultado = true;
        try{
            cnn = instance.getCnn();
            PreparedStatement st = cnn.prepareStatement("INSERT INTO `producto`( `Nombres`, `Precio`, `Stock`, `Estado`) VALUES (?,?,?,?)");
            st.setString(1, prod.getNombre());
            st.setFloat(2, prod.getPrecio());
            st.setInt(3, prod.getStock());
            st.setString(4, prod.getEstado());
            resultado = st.execute();
        }catch (SQLException ex) {
            String errorMessage = String.format(
                "Error al crear el Producto con nombre '%s', precio '%s, cantidad '%s''",prod.getNombre(), prod.getPrecio(), prod.getStock(), ex.getMessage()
            );
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
        } finally {
            instance.cerrarConexion();
        }
        return resultado;
    }

    public boolean eliminar(int id) {
        ConexionMySQL instance = ConexionMySQL.getInstance();
        Connection cnn;
        boolean resultado = true;
        try{
            cnn = instance.getCnn();
            PreparedStatement st = cnn.prepareStatement("DELETE FROM producto WHERE idProducto = ?");
            st.setInt(1, id);
            resultado = st.execute();
        }catch(SQLException ex){
            String errorMessage = String.format(
            "Error al tratar de eliminar al producto con ID '%s' ",  id, ex.getMessage()
                );
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
        }finally{
            instance.cerrarConexion();
        }
        
        return resultado;
    }

    public List<Producto> leerTodos() {
        List<Producto> Productos = new ArrayList<>();
        ConexionMySQL instance = ConexionMySQL.getInstance();
        Connection cnn;
        try{
            cnn = instance.getCnn();
            PreparedStatement st = cnn.prepareStatement("SELECT idProducto, Nombres, Precio, Stock, Estado FROM producto");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Producto prod = new Producto();
                prod.setIdProducto(rs.getInt("idProducto"));
                prod.setNombre(rs.getString("Nombres"));
                prod.setPrecio(rs.getFloat("Precio"));
                prod.setStock(rs.getInt("Stock"));
                prod.setEstado(rs.getString("Estado"));
                Productos.add(prod);
            }
        }catch(SQLException ex){
            String errorMessage = String.format(
            "Error al tratar de listar los productos ", ex.getMessage()
                );
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
        }finally{
            instance.cerrarConexion();
        }
        return Productos;
    }

    public Producto leer(int id) {
        ConexionMySQL instance = ConexionMySQL.getInstance();
        Connection cnn;
        Producto prod = null;
        try{
            cnn = instance.getCnn();
            PreparedStatement st = cnn.prepareStatement("SELECT idProducto, Nombres, Precio, Stock, Estado FROM producto WHERE idProducto = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                prod = new Producto();
                prod.setIdProducto(id);
                prod.setIdProducto(rs.getInt("idProducto"));
                prod.setNombre(rs.getString("Nombres"));
                prod.setPrecio(rs.getFloat("Precio"));
                prod.setStock(rs.getInt("Stock"));
                prod.setEstado(rs.getString("Estado"));
            }
        }catch (SQLException ex) {
            String errorMessage = String.format(
                "Error al leer al producto con id '%s''", id, ex.getMessage()
            );
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
        } finally {
            instance.cerrarConexion();
        }
        
        return prod;
    }

    public boolean editar(Producto prod) {
        ConexionMySQL instance = ConexionMySQL.getInstance();
        Connection cnn;
        boolean resultado =  true;
        try{
            cnn = instance.getCnn();
            PreparedStatement st = cnn.prepareStatement("UPDATE `producto` SET `Nombres`=?, `Precio`=?, `Stock`=?, `Estado`=? WHERE idProducto=?;");
            st.setString(1, prod.getNombre());
            st.setFloat(2, prod.getPrecio());
            st.setInt(3, prod.getStock());
            st.setString(4, prod.getEstado());
            st.setInt(6, prod.getIdProducto());
            resultado = st.execute();
        }catch (SQLException ex) {
            String errorMessage = String.format(
                "Error al editar el producto con nombre '%s', precio '%s, cantidad '%s'''",prod.getNombre(), prod.getPrecio(), prod.getStock(), ex.getMessage()
            );
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
        } finally {
            instance.cerrarConexion();
        }
        return resultado;
    }
}
