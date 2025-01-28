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
import modelo.Venta;

/**
 *
 * @author Bernal
 */
public class VentaDAO {
    public boolean insertar(Venta Ven) {
        ConexionMySQL instance = ConexionMySQL.getInstance();
        Connection cnn;
        boolean resultado = true;
        try{
            cnn = instance.getCnn();
            PreparedStatement st = cnn.prepareStatement("INSERT INTO `ventas`( `idCliente`, `idEmpleado`, `NumeroSerie`, `FechaVentas`,`Monto`,`Estado`) VALUES (?,?,?,?,?,?)");
            st.setInt(1, Ven.getIdCliente());
            st.setInt(2, Ven.getIdEmpleado());
            st.setString(3, Ven.getNumSerie());
            st.setString(4, Ven.getFecha());
            st.setFloat(5, Ven.getMonto());
            st.setString(6, Ven.getEstado());
            resultado = st.execute();
        }catch (SQLException ex) {
            String errorMessage = String.format(
                "Error al crear la venta con numero de serie '%s', el dia '%s, monto de '%s''",Ven.getNumSerie(), Ven.getFecha(), Ven.getMonto(), ex.getMessage()
            );
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
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
            PreparedStatement st = cnn.prepareStatement("DELETE FROM venta WHERE idVenta = ?");
            st.setInt(1, id);
            resultado = st.execute();
        }catch(SQLException ex){
            String errorMessage = String.format(
            "Error al tratar de eliminar la venta con ID '%s' ",  id, ex.getMessage()
                );
                Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
        }finally{
            instance.cerrarConexion();
        }
        
        return resultado;
    }

    public List<Venta> leerTodos() {
        List<Venta> Ventas = new ArrayList<>();
        ConexionMySQL instance = ConexionMySQL.getInstance();
        Connection cnn;
        try{
            cnn = instance.getCnn();
            PreparedStatement st = cnn.prepareStatement("SELECT *  FROM ventas");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Venta Ven = new Venta();
                Ven.setIdVentas(rs.getInt("idVenta"));
                Ven.setIdCliente(rs.getInt("idCliente"));
                Ven.setIdEmpleado(rs.getInt("idEmpleado"));
                Ven.setNumSerie(rs.getString("Numero de serie"));
                Ven.setFecha(rs.getString("Fecha (AAAA-MM-DD)"));
                Ven.setMonto(rs.getFloat("Monto"));
                Ven.setEstado(rs.getString("Estado"));
                Ventas.add(Ven);
            }
        }catch(SQLException ex){
            String errorMessage = String.format(
            "Error al tratar de listar las ventas ", ex.getMessage()
                );
                Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
        }finally{
            instance.cerrarConexion();
        }
        return Ventas;
    }

    public Venta leer(int id) {
        ConexionMySQL instance = ConexionMySQL.getInstance();
        Connection cnn;
        Venta Ven = null;
        try{
            cnn = instance.getCnn();
            PreparedStatement st = cnn.prepareStatement("SELECT * FROM ventas WHERE idVentas = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Ven = new Venta();
                Ven.setIdVentas(id);
                Ven.setIdCliente(rs.getInt("idCliente"));
                Ven.setIdEmpleado(rs.getInt("idEmpleado"));
                Ven.setNumSerie(rs.getString("Numero de serie"));
                Ven.setFecha(rs.getString("Fecha (AAAA-MM-DD)"));
                Ven.setMonto(rs.getFloat("Monto"));
                Ven.setEstado(rs.getString("Estado"));
            }
        }catch (SQLException ex) {
            String errorMessage = String.format(
                "Error al leer la venta con id '%s''", id, ex.getMessage()
            );
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
        } finally {
            instance.cerrarConexion();
        }
        
        return Ven;
    }

    public boolean editar(Venta Ven) {
        ConexionMySQL instance = ConexionMySQL.getInstance();
        Connection cnn;
        boolean resultado =  true;
        try{
            cnn = instance.getCnn();
            PreparedStatement st = cnn.prepareStatement("UPDATE `ventas` SET `idCliente`=?, `idEmpleado`=?, `NumeroSerie`=?, `FechaVentas`=?, `Monto`=?, `Estado`=? WHERE idVentas=?;");
            st.setInt(1, Ven.getIdCliente());
            st.setInt(2, Ven.getIdEmpleado());
            st.setString(3, Ven.getNumSerie());
            st.setString(4, Ven.getFecha());
            st.setFloat(5, Ven.getMonto());
            st.setString(6, Ven.getEstado());
            st.setInt(7, Ven.getIdVentas());
            resultado = st.execute();
        }catch (SQLException ex) {
            String errorMessage = String.format(
                "Error al editar la venta con numero de serie '%s', del dia '%s, con monto de '%s'''",Ven.getNumSerie(), Ven.getFecha(), Ven.getMonto(), ex.getMessage()
            );
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
        } finally {
            instance.cerrarConexion();
        }
        return resultado;
    }
}
