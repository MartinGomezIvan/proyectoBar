package com.example.proyectobar;

import com.example.proyectobar.domain.Bar;
import com.example.proyectobar.util.R;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConexionBD {
    private Connection conexion;

    public void conectar() throws ClassNotFoundException, SQLException, IOException {

        Properties properties= new Properties();
        String host="";
        String port="";
        String name="";
        String username="";
        String password="";

        try {
            properties.load(new FileInputStream(new File("src/main/resources/configuration/database.properties")));

            //System.out.println(properties.get("driver"));
            host=String.valueOf(properties.get("host"));
            port=String.valueOf(properties.get("port"));
            name=String.valueOf(properties.get("name"));
            username=String.valueOf(properties.get("username"));
            password=String.valueOf(properties.get("password"));
            System.out.println(host+"  "+port+"  "+name+"  "+username+"  "+password);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*Properties configuration = new Properties();
        configuration.load(R.getProperties("database.properties"));
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String name = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");
        System.out.println("username");*/

        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);
        if (conexion!=null)
            System.out.println("Conexion correcta");
    }


    public void guardarBebida(Bar bar) throws SQLException {//Insertamos el pedido en la BBDD
        String sql = "INSERT INTO Pedido (bebida, sabor, mezcla, marca, complemento) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, bar.getBebida());
        sentencia.setString(2, bar.getSabor());
        sentencia.setString(3, bar.getMezcla());
        sentencia.setString(4, bar.getMarca());
        sentencia.setString(5, bar.getComplemento());
        sentencia.executeUpdate();
    }

    public void eliminarBebida(Bar bar) throws SQLException {//Eliminamos el pedido en la BBDD
        String sql = "DELETE FROM Pedido WHERE Bebida = ? AND Sabor = ? AND Mezcla = ? AND Marca = ? AND Complemento = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, bar.getBebida());
        sentencia.setString(2, bar.getSabor());
        sentencia.setString(3, bar.getMezcla());
        sentencia.setString(4, bar.getMarca());
        sentencia.setString(5, bar.getComplemento());
        sentencia.executeUpdate();
    }
    public void eliminarTodo() throws SQLException {//Eliminamos todos los pedidos
        String sql = "DELETE FROM Pedido";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.executeUpdate();
    }



    public List<Bar> obtenerBebidas() throws SQLException {//Obtenemos las opciones de bebida
        List<Bar> bebidas = new ArrayList<>();
        String sql = "SELECT * FROM Datos";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Bar bar = new Bar();
            bar.setBebida(resultado.getString(2));
            bar.setSabor(resultado.getString(3));
            bar.setMezcla(resultado.getString(4));
            bar.setMarca(resultado.getString(5));
            bar.setComplemento(resultado.getString(6));

            bebidas.add(bar);
        }
        return  bebidas;
    }

    public List<Bar> obtenerPedidos() throws SQLException {//Obtenemos los pedidos
        List<Bar> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Bar pedido = new Bar();
            pedido.setBebida(resultado.getString(2));
            pedido.setSabor(resultado.getString(3));
            pedido.setMezcla(resultado.getString(4));
            pedido.setMarca(resultado.getString(5));
            pedido.setComplemento(resultado.getString(6));

            pedidos.add(pedido);
        }
        return  pedidos;
    }

    public void aniadirPedidosAOpciones() throws SQLException {//Metemos los pedidos en las opciones
        List<Bar> recogerPedidos=obtenerPedidos();
        String sql = "INSERT INTO Datos (bebida, sabor, mezcla, marca, complemento) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        // Por cada objeo bar en la lista recogerPedidos
        for (Bar pedido : recogerPedidos) {
            sentencia.setString(1, pedido.getBebida());
            sentencia.setString(2, pedido.getSabor());
            sentencia.setString(3, pedido.getMezcla());
            sentencia.setString(4, pedido.getMarca());
            sentencia.setString(5, pedido.getComplemento());
            sentencia.executeUpdate();
        }


    }


}
