package com.example.proyectobar;

import com.example.proyectobar.domain.Bar;
import com.example.proyectobar.util.R;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConexionBD {
    private Connection conexion;

    public void conectar() throws ClassNotFoundException, SQLException, IOException {
        Properties configuration = new Properties();
        configuration.load(R.getProperties("database.properties"));
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String name = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);
    }

    public void desconectar() throws SQLException {
        conexion.close();
    }

    public void guardarBebida(Bar bar) throws SQLException {
        String sql = "INSERT INTO Datos (bebida, sabor, mezcla, marca, complemento) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, bar.getBebida());
        sentencia.setString(2, bar.getSabor());
        sentencia.setString(3, bar.getMezcla());
        sentencia.setString(4, bar.getMarca());
        sentencia.setString(5, bar.getComplemento());
        sentencia.executeUpdate();
    }

    public void eliminarBebida(Bar bar) throws SQLException {
        String sql = "DELETE FROM coches WHERE bebida = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, bar.getBebida());
        sentencia.executeUpdate();
    }

    public void modificarBebida(Bar bebidaAntigua, Bar bebidaNueva) throws SQLException {
        String sql = "UPDATE Datos SET bebida = ?, sabor = ?, mezcla = ?, marca = ?, complemento WHERE bebida = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, bebidaNueva.getBebida());
        sentencia.setString(2, bebidaNueva.getSabor());
        sentencia.setString(3, bebidaNueva.getMezcla());
        sentencia.setString(4, bebidaNueva.getMarca());
        sentencia.setString(5, bebidaNueva.getComplemento());
        sentencia.executeUpdate();
    }

    public List<Bar> obtenerBebidas() throws SQLException {
        List<Bar> bebidas = new ArrayList<>();
        String sql = "SELECT * FROM Datos";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Bar bar = new Bar();
            bar.setBebida(resultado.getString(1));
            bar.setSabor(resultado.getString(2));
            bar.setMezcla(resultado.getString(3));
            bar.setMarca(resultado.getString(4));
            bar.setComplemento(resultado.getString(5));

            bebidas.add(bar);
        }

        return bebidas;
    }
}
