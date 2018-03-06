<%-- 
    Document   : index
    Created on : Mar 4, 2018, 11:44:25 AM
    Author     : Roy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="b4/bootstrap.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
          <nav class="navbar navbar-toggleable-md navbar-light bg-faded">
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <a class="navbar-brand" href="#">Restaurante</a>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="ComidasController?action=mostrar">Comidas <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="RecetasController?action=mostrar">Recetas</a>
                    </li>
                     <li class="nav-item">
                        <a class="nav-link" href="SugerenciasController?action=mostrar">Sugerencias</a>
                    </li>
                      <li class="nav-item">
                        <a class="nav-link" href="TipoComidasController?action=mostrar">Tipo Comidas</a>
                    </li>
                      <li class="nav-item">
                        <a class="nav-link" href="UsuariosController?action=mostrar">Usuarios</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <a href="TipoComidasController?action=nuevo">Nuevo</a>
            <table class="table">
                <thead>
                    <tr>
                        <th>id</th>
                        <th>nombre</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="tipo" items="${lista}">
                        <tr>
                            <td><c:out value="${tipo.id}"/></td>
                            <td><c:out value="${tipo.nombre}"/></td>
                            <td><a href="TipoComidasController?action=showedit&id=<c:out value="${tipo.id}" />">Editar</a></td>
                            <td><a href="TipoComidasController?action=eliminar&id=<c:out value="${tipo.id}"/>">Eliminar</a> </td>				
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>


        <script src="b4/jquery-3.3.1.min.js" type="text/javascript"></script>    
        <script src="b4/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>
