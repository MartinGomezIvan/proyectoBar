package com.example.proyectohospital;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PreguntasFrecuentes {

    @FXML
    private Button botonEnviar;

    @FXML
    private Button botonVolver;

    @FXML
    private TextField escribirCorreo;

    @FXML
    private TextArea escribirPregunta;

    @FXML
    void pulsarBotonEnviar(ActionEvent event) {
        String correo=escribirCorreo.getText();
        if(escribirPregunta.getText().isEmpty() || escribirCorreo.getText().isEmpty() || !correo.matches(".*@.*")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Debe escribir una pregunnta y un correo"+"\n"+"Recuerde que el correo debe contener una @");

            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setContentText("Se le responderá la pregunta al correo "+escribirCorreo.getText()+" en los próximos días");
            alert.showAndWait();
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

}
