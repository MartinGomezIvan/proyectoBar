package com.example.proyectobar;


import com.example.proyectobar.util.AlertUtils;
import com.example.proyectobar.domain.Bar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class Controlador {

    @FXML
    private Button botonCancelar;

    @FXML
    private Button botonEliminar;

    @FXML
    private Button botonGuardar;

    @FXML
    private Button botonModificar;

    @FXML
    private Button botonNuevo;

    @FXML
    private Button botonOpciones;

    @FXML
    private Label lbEstado;

    @FXML
    private TextField ponerBebida;

    @FXML
    private TextField ponerMezcla;

    @FXML
    private TextField ponerSabor;

    @FXML
    private ListView<Bar> salidaDatos;

    @FXML
    private ComboBox<String> seleccionarComplemento;

    @FXML
    private ComboBox<String> seleccionarMarca;
    private enum Accion {
        NUEVO, MODIFICAR
    }
    private Accion accion;

    private final ConexionBD conexionBD;
    private Bar bebidaSeleccionado;

    public Controlador() {
        conexionBD = new ConexionBD();
        try {
            conexionBD.conectar();
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("Error al conectar con la base de datos");
        } catch (ClassNotFoundException cnfe) {
            AlertUtils.mostrarError("Error al iniciar la aplicación");
        } catch (IOException ioe) {
            AlertUtils.mostrarError("Error al cargar la configuración");
        }

        System.out.println(System.getProperty("user.home"));
    }

    @FXML
    void pulsarBotonCancelar(ActionEvent event) {
        limpiarCajas();
        modoEdicion(false);
        accion = Accion.NUEVO;
    }

    @FXML
    void pulsarBotonEliminar(ActionEvent event) {
        Bar bar1 = salidaDatos.getSelectionModel().getSelectedItem();
        if (bar1 == null) {
            lbEstado.setText("ERROR: No se ha seleccionado ningún coche");
            return;
        }

        try {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Eliminar bebida");
            confirmacion.setContentText("¿Estás seguro?");
            Optional<ButtonType> respuesta = confirmacion.showAndWait();
            if (respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE)
                return;

            conexionBD.eliminarBebida(bar1);
            lbEstado.setText("MENSAJE: Coche eliminado con éxito");

            cargarDatos();
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("No se ha podido eliminar el coche");
        }
    }

    @FXML
    void pulsarBotonGuardar(ActionEvent event) {

        if(ponerBebida.getText()==null || ponerSabor.getText()==null|| ponerMezcla.getText()==null|| seleccionarMarca.getValue()==null ||seleccionarComplemento.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en datos");
            alert.setContentText("Debe completar todos los campos");
            alert.showAndWait();
            throw new RuntimeException("");
        }else {
            String bebida = ponerBebida.getText();
            String sabor = ponerSabor.getText();
            String mezcla = ponerMezcla.getText();
            String marca = seleccionarMarca.getValue();
            String complemento = seleccionarComplemento.getValue();

        }
    }

    @FXML
    void pulsarBotonModificar(ActionEvent event) {
        modoEdicion(true);
        accion = Accion.MODIFICAR;
    }

    @FXML
    void pulsarBotonNuevo(ActionEvent event) {
        limpiarCajas();
        accion = Accion.NUEVO;
    }

    @FXML
    void pulsarBotonOpciones(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("opciones"));
        try {
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            // Obtener una referencia al Stage de la ventana actual
            Stage currentStage = (Stage) botonOpciones.getScene().getWindow();
            // Cerrar la ventana actual
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void seleccionarBebida(MouseEvent event) {

    }
    private void limpiarCajas() {
        ponerBebida.setText("");
        ponerSabor.setText("");
        ponerMezcla.setText("");
        seleccionarMarca.setValue("");
        seleccionarComplemento.setValue("");

    }
    private void modoEdicion(boolean activar) {
        botonNuevo.setDisable(activar);
        botonGuardar.setDisable(!activar);
        botonModificar.setDisable(activar);
        botonEliminar.setDisable(activar);
        botonCancelar.setDisable(activar);
        botonOpciones.setDisable(activar);

        ponerBebida.setEditable(activar);
        ponerSabor.setEditable(activar);
        ponerMezcla.setEditable(activar);
        seleccionarMarca.setDisable(!activar);
        seleccionarComplemento.setDisable(activar);
    }
    private void cargarDatos() throws SQLException {// Esto es para poner los valores en los combobox

    }
}