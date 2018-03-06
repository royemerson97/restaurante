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
            <form action="UsuariosController?action=editar" method="post">
                <input type="hidden" name="id" value="${usuario.id}">
                <div class="form-group"> 
                    <label>Nombre</label>
                    <input type="text" class="form-control" name="nombre" value="${usuario.nombre}"/>
                </div>
                <div class="form-group"> 
                    <label>apellidos</label>
                    <input type="text" class="form-control" name="apellidos"  value="${usuario.apellidos}"/>
                </div>
                <div class="form-group"> 
                    <label>usuario</label>
                    <input type="text" class="form-control" name="usuario" value="${usuario.usuario}"/>
                </div>
                <div class="form-group"> 
                    <label>clave</label>
                    <input type="password" class="form-control" name="clave" value="${usuario.clave}"/>
                </div>
                <div class="form-group"> 
                    <label>permiso</label>
                    <input type="number" class="form-control" name="permiso" value="${usuario.permiso}" />
                </div>
                <input type="submit" class="btn btn-primary" value="subir">
            </form>
        </div>

        <script src="b4/jquery-3.3.1.min.js" type="text/javascript"></script>    
        <script src="b4/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>
