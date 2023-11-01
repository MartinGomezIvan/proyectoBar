package com.example.proyectobar;

import com.example.proyectobar.domain.Bar;
import com.example.proyectobar.util.AlertUtils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FinalizarPedido {

    @FXML
    private Button botonEliminar;

    @FXML
    private Button botonModificar;

    @FXML
    private Button botonPagar;

    @FXML
    private Button botonSeguirPidiendo;

    @FXML
    private Button botonVerPedidos;

    @FXML
    private ListView<Bar> salidaPedidos;
    private final ConexionBD conexionBD;
    private Controlador objControlador;
    private Bar pedidoSeleccionado;
    public FinalizarPedido() {//Constructor
        conexionBD = new ConexionBD();
        objControlador=new Controlador();
        try {
            conexionBD.conectar();
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("Error al conectar con la base de datos");
        } catch (ClassNotFoundException cnfe) {
            AlertUtils.mostrarError("Error al iniciar la aplicación");
        } catch (IOException ioe) {
            AlertUtils.mostrarError("Error al cargar la configuración");
        }
    }

    @FXML
    void pulsarBotonEliminar(ActionEvent event) {//Tenemos la opción de eliminar el pedido seleccionado
        if(pedidoSeleccionado==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en datos");
            alert.setContentText("Debe seleccionar un pedido");
            alert.showAndWait();
            throw new RuntimeException("");
        }else{

            try{
                conexionBD.eliminarBebida(pedidoSeleccionado);
                //Para que parezca como si se hubiera reinciciado, pero en realidad es meterle un array vacío
                salidaPedidos.setItems(FXCollections.observableArrayList());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    void pulsarPedidos(MouseEvent event) {//Método que nos permite pulsar sobre los pedidos
         pedidoSeleccionado = salidaPedidos.getSelectionModel().getSelectedItem();
        System.out.println(pedidoSeleccionado);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información sobre el pedido");
        alert.setContentText("Pulse el botón modificar para modificar su pedido.A continuación complete su nuevo pedido"+"\n"+"Pulse el botón eliminar para eliminar su pedido;Después pulse de nuevo el botón de ver los pedidos para confirmar que se ha elimminado");

        alert.showAndWait();
    }

    //Pulsamos el botón Modificar, y nos lleva a la clase Controlador para que podamos modoficar el pedido
    //El pedido sleccionado se borarrá en la base de datos y se sustituirá por el pedido nuevo
   @FXML
    void pulsarBotonModificar(ActionEvent event) {
        if(pedidoSeleccionado==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en datos");
            alert.setContentText("Debe seleccionar un pedido");
            alert.showAndWait();
            throw new RuntimeException("");
        }else{
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("bar.fxml"));

            try{
                //Cuando nos lleva a la clase Controlador para modificar el pedido, se nos elimina de la base de datos
                // ya que el pedido a modificar  va a cambiar
                conexionBD.eliminarBebida(pedidoSeleccionado);
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                // Obtener una referencia al Stage de la ventana actual
                Stage currentStage = (Stage) botonSeguirPidiendo.getScene().getWindow();
                // Cerrar la ventana actual
                currentStage.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    @FXML
    void pulsarBotonPagar(ActionEvent event) {//Nos lleva a la clase Pagar
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Pagar.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            // Obtener una referencia al Stage de la ventana actual
            Stage currentStage = (Stage) botonPagar.getScene().getWindow();
            // Cerrar la ventana actual
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Nos lleva a la clase Controlador para seguir piediendo
    //No se nos eliminan los peididos cuando volvemos a la otra clase
    @FXML
    void pulsarBotonSeguirPidiendo(ActionEvent event) {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("bar.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            // Obtener una referencia al Stage de la ventana actual
            Stage currentStage = (Stage) botonSeguirPidiendo.getScene().getWindow();
            // Cerrar la ventana actual
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void pulsarBotonVerPedidos(ActionEvent event) {//No permite ver nuestros pedidos
        try{
            salidaPedidos.getItems().clear();
            List<Bar> mostrarPedidos=conexionBD.obtenerPedidos();
            salidaPedidos.setItems(FXCollections.observableArrayList(mostrarPedidos).sorted());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}







