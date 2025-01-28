/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Bernal
 */
public class Cliente {
    private int idCliente;
    private String dni;
    private String nombre;
    private String Direccion;
    private String estado;
    
     public Cliente(){}
    public Cliente(int id, String dni, String nombre, String telefono, String estado, String usuario) {
        this.idCliente = id;
        this.dni = dni;
        this.nombre = nombre;
        this.Direccion = telefono;
        this.estado = estado;
    }

    public Cliente(String dni, String nombre, String telefono, String estado, String usuario) {
        this.dni = dni;
        this.nombre = nombre;
        this.Direccion = telefono;
        this.estado = estado;
    } 
    
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getdni() {
        return dni;
    }

    public void setdni(String Dni) {
        this.dni = Dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
