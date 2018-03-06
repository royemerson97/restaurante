/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Comida;
import Model.Conexion;
import Model.Receta;
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
public class RecetaDAO {
     private Conexion con;
    private Connection connection;

    public RecetaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        System.out.println(jdbcURL);
        con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
     public boolean insertar(Receta receta) throws SQLException {
        String sql = "INSERT INTO recetas (id_receta, titulo, id_usuario, ingredientes, preparacion, publicada) VALUES (null, ?,?,?,?,?)";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, receta.getTitulo());
        statement.setInt(2, receta.getId_usuario());
        statement.setString(3, receta.getIngredientes());
        statement.setString(4, receta.getPreparacion());
        statement.setInt(5, receta.getPublicada());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowInserted;
    }

    // listar todos los productos
    public List<Receta> listarArticulos() throws SQLException {

        List<Receta> listaReceta = new ArrayList<Receta>();
        String sql = "SELECT * FROM recetas";
        con.conectar();
        connection = con.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int id = resulSet.getInt("id_receta");
            String titulo = resulSet.getString("titulo");
            int id_usuario = resulSet.getInt("id_usuario");
            String ingredientes = resulSet.getString("ingredientes");
            String preparacion = resulSet.getString("preparacion");
            int publicada = resulSet.getInt("publicada");
            Receta receta = new Receta(id,titulo, id_usuario, ingredientes, preparacion,publicada );
            listaReceta.add(receta);
        }
        con.desconectar();
        return listaReceta;
    }

    // obtener por id
    public Receta obtenerPorId(int id) throws SQLException {
        Receta receta = null;

        String sql = "SELECT * FROM recetas WHERE id_receta= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        
        ResultSet res = statement.executeQuery();
        if (res.next()) {
            receta = new Receta(res.getInt("id_receta"), res.getString("titulo"), res.getInt("id_usuario")
            , res.getString("ingredientes") , res.getString("preparacion"), res.getInt("publicada"));
        }
        res.close();
        con.desconectar();
        return receta;
    }

    // actualizar
    public boolean actualizar(Receta receta) throws SQLException {
        boolean rowActualizar = false;
        String sql = "UPDATE recetas SET titulo=?, id_usuario=?, ingredientes=?, preparacion=?, publicada=?  WHERE id_receta=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, receta.getTitulo());
        statement.setInt(2, receta.getId_usuario());
        statement.setString(3, receta.getIngredientes());
        statement.setString(4, receta.getPreparacion());
        statement.setInt(5, receta.getPublicada());
        statement.setInt(6, receta.getId());
        rowActualizar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowActualizar;
    }

    //eliminar
    public boolean eliminar(Receta receta) throws SQLException {
        boolean rowEliminar = false;
        String sql = "DELETE FROM recetas WHERE id_receta=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, receta.getId());
        rowEliminar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowEliminar;
    }
    
}
