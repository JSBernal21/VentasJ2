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
import modelo.Cliente;

/**
 *
 * @author Bernal
 */
public class ClienteDAO {
   
    
    //******* Operaciones CRUD *******//         
    public boolean insertar(Cliente clie) {
        ConexionMySQL instance = ConexionMySQL.getInstance();
        Connection cnn;
        boolean resultado = true;
        try{
            cnn = instance.getCnn();
            PreparedStatement st = cnn.prepareStatement("INSERT INTO `cliente`(`dni`, `Nombres`, `Direccion`, `Estado`, `Username`) VALUES (?,?,?,?,?)");
            st.setString(1, clie.getdni());
            st.setString(2, clie.getNombre());
            st.setString(3, clie.getDireccion());
            st.setString(4, clie.getEstado());
            resultado = st.execute();
        }catch (SQLException ex) {
            String errorMessage = String.format(
                "Error al crear el cliente con nombre '%s', DNI '%s, Direccion '%s'",clie.getNombre(), clie.getdni(), clie.getDireccion(), ex.getMessage()
            );
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
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
            PreparedStatement st = cnn.prepareStatement("DELETE FROM cliente WHERE idCliente = ?");
            st.setInt(1, id);
            resultado = st.execute();
        }catch(SQLException ex){
            String errorMessage = String.format(
            "Error al tratar de eliminar al cliente con ID '%s' ",  id, ex.getMessage()
                );
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
        }finally{
            instance.cerrarConexion();
        }
        
        return resultado;
    }

    public List<Cliente> leerTodos() {
        List<Cliente> clientes = new ArrayList<>();
        ConexionMySQL instance = ConexionMySQL.getInstance();
        Connection cnn;
        try{
            cnn = instance.getCnn();
            PreparedStatement st = cnn.prepareStatement("SELECT idCliente, dni, Nombres, Direccion, Estado, Username FROM cliente");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Cliente em = new Cliente();
                em.setIdCliente(rs.getInt("idCliente"));
                em.setdni(rs.getString("dni"));
                em.setNombre(rs.getString("Nombres"));
                em.setDireccion(rs.getString("Direccion"));
                em.setEstado(rs.getString("Estado"));
                clientes.add(em);
            }
        }catch(SQLException ex){
            String errorMessage = String.format(
            "Error al tratar de listar los clientes ", ex.getMessage()
                );
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
        }finally{
            instance.cerrarConexion();
        }
        return clientes;
    }

    public Cliente leer(String Dni) {
        ConexionMySQL instance = ConexionMySQL.getInstance();
        Connection cnn;
        Cliente clie = null;
        try{
            cnn = instance.getCnn();
            PreparedStatement st = cnn.prepareStatement("SELECT idCliente, Dni, Nombres, Direccion, Estado FROM cliente WHERE Dni = ?");
            st.setString(1, Dni);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                clie = new Cliente();
                clie.setIdCliente(rs.getInt("idCliente"));
                clie.setdni(Dni);
                clie.setNombre(rs.getString("Nombres"));
                clie.setDireccion(rs.getString("Direccion"));
                clie.setEstado(rs.getString("Estado"));
            }
            System.out.println("cliente: "+clie.getNombre()+"  "+clie.getdni());
        }catch (SQLException ex) {
            String errorMessage = String.format(
                "Error al leer al cliente con Dni '%s''", Dni, ex.getMessage()
            );
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
        } finally {
            instance.cerrarConexion();
        }
        
        return clie;
    }

    public boolean editar(Cliente clie) {
        ConexionMySQL instance = ConexionMySQL.getInstance();
        Connection cnn;
        boolean resultado =  true;
        try{
            cnn = instance.getCnn();
            PreparedStatement st = cnn.prepareStatement("UPDATE `cliente` SET `dni`=?, `Nombres`=?, `Direccion`=?, `Estado`=?, `Username`=? WHERE idCliente=?;");
            st.setString(1, clie.getdni());
            st.setString(2, clie.getNombre());
            st.setString(3, clie.getDireccion());
            st.setString(4, clie.getEstado());
            st.setInt(6, clie.getIdCliente());
            resultado = st.execute();
        }catch (SQLException ex) {
            String errorMessage = String.format(
                "Error al editar el cliente con nombre '%s', DNI '%s, Direccion '%s'",clie.getNombre(), clie.getdni(), clie.getDireccion(),  ex.getMessage()
            );
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
        } finally {
            instance.cerrarConexion();
        }
        return resultado;
    }
}
