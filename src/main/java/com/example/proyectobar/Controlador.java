package com.example.proyectobar;


import com.example.proyectobar.util.AlertUtils;
import com.example.proyectobar.domain.Bar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controlador implements Initializable {
    @FXML
    private Button botonConfirmar;

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
    private TableView<Bar> salidaDatos;

    @FXML
    private ComboBox<String> seleccionarComplemento;

    @FXML
    private ComboBox<String> seleccionarMarca;
    @FXML
    private TableColumn<Bar, String> columnaBebida;

    @FXML
    private TableColumn<Bar, String> columnaComplemento;

    @FXML
    private TableColumn<Bar, String> columnaMarca;

    @FXML
    private TableColumn<Bar, String> columnaMezcla;

    @FXML
    private TableColumn<Bar, String> columnaSabor;
    ObservableList<Bar> resultados;



    private enum Accion {
        NUEVO, MODIFICAR
    }
    private Accion accion;

    private final ConexionBD conexionBD;
    private Bar bebidaSeleccionado;
    String marcaBuena="";
    String complementoBueno="";

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

        //System.out.println("user.home");
    }
    @FXML
    void pulsarBotonConfirmar(ActionEvent event) {

    }
    @FXML
    void pulsarBotonCancelar(ActionEvent event) {
        limpiarCajas();
        accion = Accion.NUEVO;
    }

    @FXML
    void pulsarBotonEliminar(ActionEvent event) {
        /*Bar bar1 = salidaDatos.getSelectionModel().getSelectedItem();
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

        } catch (SQLException sqle) {
            AlertUtils.mostrarError("No se ha podido eliminar el coche");
        }*/
    }

    @FXML
    void pulsarBotonGuardar(ActionEvent event) {

        if (ponerBebida.getText() == null || ponerSabor.getText() == null || ponerMezcla.getText() == null || seleccionarMarca.getValue() == null || seleccionarComplemento.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en datos");
            alert.setContentText("Debe completar todos los campos");
            alert.showAndWait();
            throw new RuntimeException("");
        } else {
            marcaBuena = seleccionarMarca.getValue();
            complementoBueno = seleccionarComplemento.getValue();

        }
        aniadir();
    }
        private void aniadir(){
            Bar bar= new Bar();
            bar.bebida.set(ponerBebida.getText());
            bar.sabor.set(ponerSabor.getText());
            bar.mezcla.set(ponerMezcla.getText());
            bar.marca.set(marcaBuena);
            bar.complemento.set(complementoBueno);
            resultados.add(bar);
            System.out.println(resultados);

        }


    @FXML
    void pulsarBotonModificar(ActionEvent event) {
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] marca = new String[]{"Negrita","Brugal","La Recompensa", "Burbon","JB","DYC","Cardú","Jagger","Burbon","Belvedere", "Eristoff", "Barceló", "Puerto de Indias","Seagram's","Tanqueray"};
        seleccionarMarca.setItems(FXCollections.observableArrayList(marca));
        String[]complementos=new String[]{"Rodaja Limon","Rodaja Naranja", "Gominolas","Hielo","Gotas de licor",};
        seleccionarComplemento.setItems(FXCollections.observableArrayList(complementos));

        columnaBebida.setCellValueFactory(new PropertyValueFactory<>("bebida"));
        columnaComplemento.setCellValueFactory(new PropertyValueFactory<>("complemento"));
        columnaMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        columnaMezcla.setCellValueFactory(new PropertyValueFactory<>("mezcla"));//Atributos creados
        columnaSabor.setCellValueFactory(new PropertyValueFactory<>("sabor"));//Atributos creados

        resultados= FXCollections.observableArrayList();
        salidaDatos.setItems(resultados);
    }



}