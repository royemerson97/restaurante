/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Comida;
import Model.Conexion;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Roy
 */
public class UsuarioDAO {

    private Conexion con;
    private Connection connection;

    public UsuarioDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
        System.out.println(jdbcURL);
        con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public boolean insertar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (id_usuario, nombre, apellidos, usuario, clave, permiso ) VALUES (null,?,?,?,?,?)";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getNombre());
        statement.setString(2, usuario.getApellidos());
        statement.setString(3, usuario.getUsuario());
        statement.setString(4, usuario.getClave());
        statement.setInt(5, usuario.getPermiso());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowInserted;
    }

    // listar todos los productos
    public List<Usuario> listarArticulos() throws SQLException {

        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        String sql = "SELECT * FROM usuarios";
        con.conectar();
        connection = con.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int id_usuario = resulSet.getInt("id_usuario");
    
            String nombre = resulSet.getString("nombre");
             String apellidos = resulSet.getString("apellidos");
              String usuario = resulSet.getString("usuario");
                  String clave = resulSet.getString("clave");
            int permiso = resulSet.getInt("permiso");

            Usuario osuario = new Usuario(id_usuario, nombre, apellidos, usuario, clave, permiso);
            listaUsuarios.add(osuario);
        }
        con.desconectar();
        return listaUsuarios;
    }

    // obtener por id
    public Usuario obtenerPorId(int id) throws SQLException {
        Usuario usuario = null;

        String sql = "SELECT * FROM usuarios WHERE id_usuario= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet res = statement.executeQuery();
        if (res.next()) {
            usuario = new Usuario(res.getInt("id_usuario"), res.getString("nombre"),res.getString("apellidos"),
                      res.getString("usuario"),res.getString("clave"), res.getInt("permiso"));
        }
        res.close();
        con.desconectar();

        return usuario;
    }

    // actualizar
    public boolean actualizar(Usuario usuario) throws SQLException {
        boolean rowActualizar = false;
        String sql = "UPDATE usuarios SET nombre=?, apellidos=?, usuario=? ,clave=?, permiso=? WHERE id_usuario=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getNombre());
        statement.setString(2, usuario.getApellidos());
        statement.setString(3, usuario.getUsuario());
        statement.setString(4, usuario.getClave());
        statement.setInt(5, usuario.getPermiso());
        statement.setInt(6, usuario.getId());
        rowActualizar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowActualizar;
    }

    //eliminar
    public boolean eliminar(Usuario usuario) throws SQLException {
        boolean rowEliminar = false;
        String sql = "DELETE FROM usuarios WHERE id_usuario=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, usuario.getId());
        rowEliminar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowEliminar;
    }

}
