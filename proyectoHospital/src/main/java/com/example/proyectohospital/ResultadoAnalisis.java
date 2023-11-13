package com.example.proyectohospital;

import domain.Datos.Cita;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;


public class ResultadoAnalisis implements Initializable {

    @FXML
    private Button botonConsultar;

    @FXML
    private Button botonVolver;

    @FXML
    private ListView<Cita> salidaConsultas;
    private final CitaDAO citaDAO;
    private Cita consultaSeleccionada;
    public ResultadoAnalisis(){
        citaDAO=new CitaDAO();

    }

    @FXML
    void pulsarBotonConsultar(ActionEvent event) throws ParseException {
        if(consultaSeleccionada==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar una consulta");

            alert.showAndWait();
        }else{
            String[] opciones = {"Venga dentro de un mes para otra revisión", "Tómese 5 paracetamol a la semana", "Necesita operación, le llamaremos cuanto antes", "Con reposo durante 1 mes es suficiente", "Ya está curado"};
            Random random = new Random();
            int indiceSeleccionado = random.nextInt(opciones.length);
            String opcionSeleccionada = opciones[indiceSeleccionado];

            String fecha=consultaSeleccionada.getFechaCita();
            SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
            Date fechaRecogida=formato.parse(fecha);
            Date fechaActual=new Date();

            if (fechaRecogida==fechaActual || fechaRecogida.toInstant().isBefore(fechaActual.toInstant())){
                //Esto es para saber si la fecha que he emtido es igual a la actual o si la fecha ya ha pasado
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Resultado");
                alert.setContentText(opcionSeleccionada);
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Resultado");
                alert.setContentText("El resultado saldrá después de celebrarse la consulta");
                alert.showAndWait();
            }

        }
    }

    @FXML
    void pulsarBotonVolver(ActionEvent event) {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("MenuInicial.fxml"));
        try{
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            // Obtener una referencia al Stage de la ventana actual
            Stage currentStage = (Stage) botonVolver.getScene().getWindow();
            // Cerrar la ventana actual
            currentStage.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void seleccionarConsulta(MouseEvent event) {
        consultaSeleccionada=salidaConsultas.getSelectionModel().getSelectedItem();

    }
    public void obtenerCitas(){
//        List<Cita> obtenerCitas= citaDAO.obtenerDatos();
//        salidaConsultas.setItems(FXCollections.observableArrayList(obtenerCitas));
        salidaConsultas.getItems().setAll(citaDAO.obtenerDatos());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        obtenerCitas();
    }
}
