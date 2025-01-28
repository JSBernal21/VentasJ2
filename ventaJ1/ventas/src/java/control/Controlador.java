/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import Persistencia.EmpleadoDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Empleado;
import modelo.Cliente;
import modelo.Producto;
import modelo.Venta;
import Persistencia.ClienteDAO;
import Persistencia.EmpleadoDAO;
import Persistencia.ProductoDAO;
import Persistencia.VentaDAO;

/**
 *
 * @author oscar
 */
public class Controlador extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Cliente c =new Cliente();
    ClienteDAO cdao = new ClienteDAO();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String menu = request.getParameter("menu");
            String accion = request.getParameter("accion");
            
            if(menu.equals("Principal")){
                request.getRequestDispatcher("Principal.jsp").forward(request, response);
            }
            if(menu.equals("producto")){
                request.getRequestDispatcher("Producto.jsp").forward(request, response);
            }
            if(menu.equals("empleado")){
//                Empleado em = new Empleado();
                EmpleadoDAO edao = new EmpleadoDAO();
                int id;
                String dni, nombre, usuario, estado, telefono;
                Empleado emp;
                switch(accion){
                    case "Listar" -> {
                        List<Empleado> lista = edao.leerTodos();
                        request.setAttribute("empleados", lista);
                        request.getRequestDispatcher("Empleado.jsp").forward(request, response);
                        break;
                    } 
                    case "Editar" -> {
                        emp = edao.leer(Integer.parseInt(request.getParameter("idEmpleado")));
                        request.setAttribute("empleado", emp);
                        request.getRequestDispatcher("Controlador?menu=empleado&accion=Listar").forward(request, response);
                        break;
                    }
                    case "Actualizar" -> {
                        id = Integer.parseInt(request.getParameter("txtId"));
                        dni = request.getParameter("txtDni");
                        nombre = request.getParameter("txtNombre");
                        telefono = request.getParameter("txtTelefono");
                        estado = request.getParameter("txtEstado");
                        usuario = request.getParameter("txtUsuario");
                        edao.editar( new Empleado(id, dni, nombre, telefono, estado, usuario));
                        request.getRequestDispatcher("Controlador?menu=empleado&accion=Listar").forward(request, response);
                        break;
                    }
                    case "Agregar" -> {
                        dni = request.getParameter("txtDni");
                        nombre = request.getParameter("txtNombre");
                        telefono = request.getParameter("txtTelefono");
                        estado = request.getParameter("txtEstado");
                        usuario = request.getParameter("txtUsuario");
                        edao.insertar(new Empleado(dni, nombre, telefono, estado, usuario));
                        request.getRequestDispatcher("Controlador?menu=empleado&accion=Listar").forward(request, response);
                        break;
                    }
                    case "Eliminar" -> {
                        id = Integer.parseInt(request.getParameter("idEmpleado"));
                        edao.eliminar(id);
                        request.getRequestDispatcher("Controlador?menu=empleado&accion=Listar").forward(request, response);
                        break;
                    }
                }
            }
            if(menu.equals("clientes")){
                request.getRequestDispatcher("Clientes.jsp").forward(request, response);
            }
            if(menu.equals("nuevaVenta")){
                switch (accion) {
                    case "BuscarCliente":
                        String dni= request.getParameter("codigocliente");
                        c.setdni(dni);
                        Cliente cl=cdao.leer(dni);
                        System.out.println("cliente: "+ cl.getNombre()+ "   "+cl.getdni());
                        request.setAttribute("c", cl);
                        
                        break; 
                        
                }
                request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
            }
            
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
