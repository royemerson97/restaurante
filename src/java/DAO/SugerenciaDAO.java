/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Comida;
import Model.Conexion;
import Model.Sugerencia;
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
public class SugerenciaDAO {

    private Conexion con;
    private Connection connection;

    public SugerenciaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        System.out.println(jdbcURL);
        con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
    }
        //Insertar
        public boolean insertar(Sugerencia sugerencia) throws SQLException {
        String sql = "INSERT INTO sugerencias (id_sugerencia, id_usuario, sugerencia, publicada ) VALUES (null,?,?,?)";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, sugerencia.getId_usuario());
        statement.setString(2, sugerencia.getSugerencia());
        statement.setFloat(3, sugerencia.getPublicada());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowInserted;
    }

    // listar todas las sugerencias
    public List<Sugerencia> listaSugerencias() throws SQLException {

        List<Sugerencia> listaSugerencia = new ArrayList<Sugerencia>();
        String sql = "SELECT * FROM sugerencias";
        con.conectar();
        connection = con.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int id_sugerencia = resulSet.getInt("id_sugerencia");
            int id_usuario = resulSet.getInt("id_usuario");
            String sugerencia = resulSet.getString("sugerencia");
            int publicada = resulSet.getInt("publicada");

            Sugerencia osugerencia = new Sugerencia(id_sugerencia, id_usuario, sugerencia, publicada);
            listaSugerencia.add(osugerencia);
        }
        con.desconectar();
        return listaSugerencia;
    }

    // obtener por id
    public Sugerencia obtenerPorId(int id) throws SQLException {
        Sugerencia sugerencia = null;

        String sql = "SELECT * FROM sugerencias WHERE id_sugerencia= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet res = statement.executeQuery();
        if (res.next()) {
            sugerencia = new Sugerencia(res.getInt("id_sugerencia"), res.getInt("id_usuario"), res.getString("sugerencia"),
                    res.getInt("publicada"));
        }
        res.close();
        con.desconectar();

        return sugerencia;
    }

    // actualizar
    public boolean actualizar(Sugerencia sugerencia) throws SQLException {
        boolean rowActualizar = false;
        String sql = "UPDATE sugerencias SET id_usuario=?,sugerencia=?,publicada=? WHERE id_sugerencia=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, sugerencia.getId_usuario());
        statement.setString(2, sugerencia.getSugerencia());
        statement.setFloat(3, sugerencia.getPublicada());
        statement.setInt(4, sugerencia.getId());

        rowActualizar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowActualizar;
    }

    //eliminar
    public boolean eliminar(Sugerencia sugerencia) throws SQLException {
        boolean rowEliminar = false;
        String sql = "DELETE FROM sugerencias WHERE id_sugerencia=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, sugerencia.getId());
        rowEliminar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowEliminar;
    }

}
