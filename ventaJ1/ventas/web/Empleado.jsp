<%-- 
    Document   : Usuario
    Created on : 18/01/2025, 11:54:53 a. m.
    Author     : oscar
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
    </head>
    <body>
        <div class="d-flex">
        <div class="col-sm-5">
            <div class="card">
            <div class="card-body">
                <form action="Controlador?menu=empleado" method="post">
                    <input type="hidden" name="txtId" value="${empleado.getId()}" />
                    <div class="form-group my-3">
                        <label for="txtDni">Dni</label>
                        <input type="text" id="txtDni" name="txtDni" value="${empleado.getDni()}" class="form-control" required/>
                    </div>
                    <div class="form-group my-3">
                        <label for="txtNombres">Nombres</label>
                        <input type="text" id="txtNombres" name="txtNombre" value="${empleado.getNombre()}" class="form-control"/>
                    </div>
                    <div class="form-groupmy-3">
                        <label for="txtTelefono">Telefono</label>
                        <input type="text" id="txtTelefono" name="txtTelefono" value="${empleado.getTelefono()}" class="form-control"/>
                    </div>
                    <div class="form-group my-3">
                        <label for="txtEstado">Estado</label>
                        <input type="text" id="txtEstado" name="txtEstado" value="${empleado.getEstado()}" class="form-control" required   maxlength="1" pattern=".{1}"/>
                    </div>
                    <div class="form-group my-3">
                        <label for="txtUsuario">Usuario</label>
                        <input type="text" id="txtUsuario" name="txtUsuario" value="${empleado.getUsuario()}" class="form-control"/>
                    </div>
                    <input type="submit" class="btn btn-primary" name="accion" value="Agregar"/>
                    <input type="submit" class="btn btn-success" name="accion" value="Actualizar"/>
                </form>
            </div>
        </div>
        </div>
        <div class="col-sm-7">
            <table class="table">
                <thead>
                    <tr>    
                        <th>ID</th>
                        <th>DNI</th>
                        <th>NOMBRES</th>
                        <th>TELEFONO</th>
                        <th>ESTADO</th>
                        <th>USER</th>
                        <th>ACCIONES</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="em" items="${empleados}">
                    <tr>
                        <td>${em.getId()}</td>
                        <td>${em.getDni()}</td>
                        <td>${em.getNombre()}</td>
                        <td>${em.getTelefono()}</td>
                        <td>${em.getEstado()}</td>
                        <td>${em.getUsuario()}</td>
                        <td>
                            <a class="btn btn-warning" href="Controlador?menu=empleado&accion=Editar&idEmpleado=${em.getId()}">Editar</a>
                            <a class="btn btn-danger" href="Controlador?menu=empleado&accion=Eliminar&idEmpleado=${em.getId()}">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        </div>
    </body>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</html>
