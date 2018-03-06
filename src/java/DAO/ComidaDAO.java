/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Comida;
import Model.Conexion;
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
public class ComidaDAO {

    private Conexion con;
    private Connection connection;

    public ComidaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
        System.out.println(jdbcURL);
        con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public boolean insertar(Comida comida) throws SQLException {
        String sql = "INSERT INTO comidas (id_comida, id_tipo_comida, comida, precio ) VALUES (null,?,?,?)";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, comida.getId_tipo_comida());
        statement.setString(2, comida.getComida());
        statement.setFloat(3, comida.getPrecio());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowInserted;
    }

    // listar todos los productos
    public List<Comida> listarArticulos() throws SQLException {

        List<Comida> listaComida = new ArrayList<Comida>();
        String sql = "SELECT * FROM comidas";
        con.conectar();
        connection = con.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
            int id = resulSet.getInt("id_comida");
            int id_tipo_comida = resulSet.getInt("id_tipo_comida");
            String comida = resulSet.getString("comida");
            float precio = resulSet.getFloat("precio");

            Comida ocomida = new Comida(id, id_tipo_comida, comida, precio);
            listaComida.add(ocomida);
        }
        con.desconectar();
        return listaComida;
    }

    // obtener por id
    public Comida obtenerPorId(int id) throws SQLException {
        Comida comida = null;

        String sql = "SELECT * FROM comidas WHERE id_comida= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet res = statement.executeQuery();
        if (res.next()) {
            comida = new Comida(res.getInt("id_comida"), res.getInt("id_tipo_comida"), res.getString("comida"),
                    res.getFloat("precio"));
        }
        res.close();
        con.desconectar();

        return comida;
    }

    // actualizar
    public boolean actualizar(Comida comida) throws SQLException {
        boolean rowActualizar = false;
        String sql = "UPDATE comidas SET id_tipo_comida=?,comida=?,precio=? WHERE id_comida=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, comida.getId_tipo_comida());
        statement.setString(2, comida.getComida());
        statement.setFloat(3, comida.getPrecio());
        statement.setInt(4, comida.getId());

        rowActualizar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowActualizar;
    }

    //eliminar
    public boolean eliminar(Comida comida) throws SQLException {
        boolean rowEliminar = false;
        String sql = "DELETE FROM comidas WHERE id_comida=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, comida.getId());
        rowEliminar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowEliminar;
    }

}
