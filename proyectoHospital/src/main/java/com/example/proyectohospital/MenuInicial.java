package com.example.proyectohospital;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuInicial {

    @FXML
    private Button botonListaEspera;

    @FXML
    private Button botonPedirCita;

    @FXML
    private Button botonPreuntasFrecuentes;

    @FXML
    private Button botonResultadoAnalisis;

    @FXML
    void pulsarBotonListaEspera(ActionEvent event) {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("ListaEspera.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            // Obtener una referencia al Stage de la ventana actual
            Stage currentStage = (Stage) botonListaEspera.getScene().getWindow();
            // Cerrar la ventana actual
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void pulsarBotonPedirCita(ActionEvent event) {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("PedirCita.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            // Obtener una referencia al Stage de la ventana actual
            Stage currentStage = (Stage) botonPedirCita.getScene().getWindow();
            // Cerrar la ventana actual
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void pulsarBotonPreguntasFrecuentes(ActionEvent event) {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("PreguntasFrecuentes.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            // Obtener una referencia al Stage de la ventana actual
            Stage currentStage = (Stage) botonPreuntasFrecuentes.getScene().getWindow();
            // Cerrar la ventana actual
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void pulsarBotonResultadoAnalisis(ActionEvent event) {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("ResultadoAnalisis.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            // Obtener una referencia al Stage de la ventana actual
            Stage currentStage = (Stage) botonResultadoAnalisis.getScene().getWindow();
            // Cerrar la ventana actual
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
