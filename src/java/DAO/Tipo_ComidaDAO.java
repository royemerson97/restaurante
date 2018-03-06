/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Comida;
import Model.Conexion;
import Model.Tipo_Comida;
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
public class Tipo_ComidaDAO {
      private Conexion con;
    private Connection connection;

    public Tipo_ComidaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        System.out.println(jdbcURL);
        con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    public boolean insertar(Tipo_Comida comida) throws SQLException {
        String sql = "INSERT INTO tipo_comida (id_tipo_comida,nombre ) VALUES (null,?)";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, comida.getNombre());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowInserted;
    }

    // listar todos los productos
    public List<Tipo_Comida> listarArticulos() throws SQLException {

        List<Tipo_Comida> listaComida = new ArrayList<Tipo_Comida>();
        String sql = "SELECT * FROM tipo_comida";
        con.conectar();
        connection = con.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);

        while (resulSet.next()) {
     
            int id_tipo_comida = resulSet.getInt("id_tipo_comida");
            String nombre = resulSet.getString("nombre");
        

            Tipo_Comida ocomida = new Tipo_Comida(id_tipo_comida, nombre);
            listaComida.add(ocomida);
        }
        con.desconectar();
        return listaComida;
    }

    // obtener por id
    public Tipo_Comida obtenerPorId(int id) throws SQLException {
        Tipo_Comida comida = null;

        String sql = "SELECT * FROM tipo_comida WHERE id_tipo_comida= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet res = statement.executeQuery();
        if (res.next()) {
            comida = new Tipo_Comida(res.getInt("id_tipo_comida"), res.getString("nombre"));
        }
        res.close();
        con.desconectar();
        return comida;
    }

    // actualizar
    public boolean actualizar(Tipo_Comida comida) throws SQLException {
        boolean rowActualizar = false;
        String sql = "UPDATE tipo_comida SET nombre=? WHERE id_tipo_comida=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, comida.getNombre());
        statement.setInt(2, comida.getId());
        rowActualizar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowActualizar;
    }

    //eliminar
    public boolean eliminar(Tipo_Comida comida) throws SQLException {
        boolean rowEliminar = false;
        String sql = "DELETE FROM tipo_comida WHERE id_tipo_comida=?";
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
