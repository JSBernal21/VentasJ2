/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import config.ConexionMySQL;
import config.ContratoDAO;
import java.util.List;
import modelo.Empleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */
public class EmpleadoDAO implements ContratoDAO<Empleado>{

     
    public Empleado validar(String username, String dni) {
    Empleado emp = null;
    ConexionMySQL instance = ConexionMySQL.getInstance();
    Connection cnn;
    try {
        cnn = instance.getCnn();
        java.sql.PreparedStatement st = cnn.prepareStatement("SELECT idEmpleado, Dni, Nombres, Telefono, Estado, Username FROM empleado WHERE Username = ? AND Dni = ?");
        st.setString(1, username);
        st.setString(2, dni);

        ResultSet rs = st.executeQuery(); // Execute returns a boolean, while executeUpdate returns the number of affected rows
        if (rs.next()) {
            emp = new Empleado(
                    rs.getInt("idEmpleado"),
                    rs.getString("Dni"),
                    rs.getString("Nombres"),
                    rs.getString("Telefono"),
                    rs.getString("Estado"),
                    rs.getString("Username")
            );
        }
    } catch (SQLException ex) {
        String errorMessage = String.format(
            "Error al validar el empleado con username '%s' y DNI '%s'. Detalle: %s",
            username, dni, ex.getMessage()
        );
        Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
    } finally {
        instance.cerrarConexion();
    }
    return emp;
}
    
    @Override
    public boolean insertar(Empleado emp) {
        ConexionMySQL instance = ConexionMySQL.getInstance();
        Connection cnn;
        boolean resultado = true;
        try{
            cnn = instance.getCnn();
            PreparedStatement st = cnn.prepareStatement("INSERT INTO `empleado`(`Dni`, `Nombres`, `Telefono`, `Estado`, `Username`) VALUES (?,?,?,?,?)");
            st.setString(1, emp.getDni());
            st.setString(2, emp.getNombre());
            st.setString(3, emp.getTelefono());
            st.setString(4, emp.getEstado());
            st.setString(5, emp.getUsuario());
            resultado = st.execute();
        }catch (SQLException ex) {
            String errorMessage = String.format(
                "Error al crear el empleado con nombre '%s', DNI '%s, Telefono '%s', username '%s''",emp.getNombre(), emp.getDni(), emp.getTelefono(), emp.getUsuario(), ex.getMessage()
            );
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
        } finally {
            instance.cerrarConexion();
        }
        return resultado;
    }

    @Override
    public boolean eliminar(int id) {
        ConexionMySQL instance = ConexionMySQL.getInstance();
        Connection cnn;
        boolean resultado = true;
        try{
            cnn = instance.getCnn();
            PreparedStatement st = cnn.prepareStatement("DELETE FROM empleado WHERE idEmpleado = ?");
            st.setInt(1, id);
            resultado = st.execute();
        }catch(SQLException ex){
            String errorMessage = String.format(
            "Error al tratar de eliminar al empleado con ID '%s' ",  id, ex.getMessage()
                );
                Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
        }finally{
            instance.cerrarConexion();
        }
        
        return resultado;
    }

    @Override
    public List<Empleado> leerTodos() {
        List<Empleado> empleados = new ArrayList<>();
        ConexionMySQL instance = ConexionMySQL.getInstance();
        Connection cnn;
        try{
            cnn = instance.getCnn();
            PreparedStatement st = cnn.prepareStatement("SELECT idEmpleado, Dni, Nombres, Telefono, Estado, Username FROM empleado");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Empleado em = new Empleado();
                em.setId(rs.getInt("idEmpleado"));
                em.setDni(rs.getString("Dni"));
                em.setNombre(rs.getString("Nombres"));
                em.setTelefono(rs.getString("Telefono"));
                em.setEstado(rs.getString("Estado"));
                em.setUsuario(rs.getString("Username"));
                empleados.add(em);
            }
        }catch(SQLException ex){
            String errorMessage = String.format(
            "Error al tratar de listar los empleados ", ex.getMessage()
                );
                Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
        }finally{
            instance.cerrarConexion();
        }
        return empleados;
    }

    @Override
    public Empleado leer(int id) {
        ConexionMySQL instance = ConexionMySQL.getInstance();
        Connection cnn;
        Empleado emp = null;
        try{
            cnn = instance.getCnn();
            PreparedStatement st = cnn.prepareStatement("SELECT Dni, Nombres, Telefono, Estado, Username FROM empleado WHERE idEmpleado = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                emp = new Empleado();
                emp.setId(id);
                emp.setDni(rs.getString("Dni"));
                emp.setNombre(rs.getString("Nombres"));
                emp.setTelefono(rs.getString("Telefono"));
                emp.setEstado(rs.getString("Estado"));
                emp.setUsuario(rs.getString("Username"));
            }
        }catch (SQLException ex) {
            String errorMessage = String.format(
                "Error al leer al empleado con id '%s''", id, ex.getMessage()
            );
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
        } finally {
            instance.cerrarConexion();
        }
        
        return emp;
    }

    @Override
    public boolean editar(Empleado emp) {
        ConexionMySQL instance = ConexionMySQL.getInstance();
        Connection cnn;
        boolean resultado =  true;
        try{
            cnn = instance.getCnn();
            PreparedStatement st = cnn.prepareStatement("UPDATE `empleado` SET `Dni`=?, `Nombres`=?, `Telefono`=?, `Estado`=?, `Username`=? WHERE idEmpleado=?;");
            st.setString(1, emp.getDni());
            st.setString(2, emp.getNombre());
            st.setString(3, emp.getTelefono());
            st.setString(4, emp.getEstado());
            st.setString(5, emp.getUsuario());
            st.setInt(6, emp.getId());
            resultado = st.execute();
        }catch (SQLException ex) {
            String errorMessage = String.format(
                "Error al editar el empleado con nombre '%s', DNI '%s, Telefono '%s', username '%s''",emp.getNombre(), emp.getDni(), emp.getTelefono(), emp.getUsuario(), ex.getMessage()
            );
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, errorMessage, ex);
        } finally {
            instance.cerrarConexion();
        }
        return resultado;
    }
    
}
