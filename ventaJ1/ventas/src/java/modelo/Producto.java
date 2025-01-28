/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
/**
 *
 * @author Bernal
 */
public class Producto {
    private int IdProducto;
    private String Nombre;
    private float Precio;
    private int Stock;
    private String Estado;
    
    public Producto(){}
    public Producto(int IdProducto, String nombre, float precio, int stock, String estado) {
        this.IdProducto = IdProducto;
        this.Nombre = nombre;
        this.Precio = precio;
        this.Stock = stock;
        this.Estado = estado;
    }

    public Producto(String nombre, float precio, int stock, String estado) {
        this.Nombre = nombre;
        this.Precio = precio;
        this.Stock = stock;
        this.Estado = estado;
    }
    
    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float precio) {
        this.Precio = precio;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        this.Stock = stock;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        this.Estado = estado;
    }
}
