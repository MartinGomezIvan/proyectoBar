//package com.example.proyectohospital;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextArea;
//import javafx.stage.Stage;
//
//import java.net.URL;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Random;
//import java.util.ResourceBundle;
//
//public class VerConsulta implements Initializable {
//
//    @FXML
//    private Button botonVolver;
//
//    @FXML
//    private TextArea salidaResultado;
//
//    @FXML
//    void pulsarBotonVolver(ActionEvent event) {
//
//        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("ResultadoAnalisis.fxml"));
//        try{
//            Parent root = fxmlLoader.load();
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.show();
//            // Obtener una referencia al Stage de la ventana actual
//            Stage currentStage = (Stage) botonVolver.getScene().getWindow();
//            // Cerrar la ventana actual
//            currentStage.close();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public void recogidaDatos(String decision){
//        if (decision.equals("no")) {
//            salidaResultado.setText("El resultado saldrá el día en que se pase la consulta");
//        } else {
//            String[] opciones = {"Venga dentro de un mes para otra revisión", "Tómese 5 paracetamol a la semana", "Necesita operación, le llamaremos cuanto antes", "Con reposo durante 1 mes es suficiente", "Ya está curado"};
//            Random random = new Random();
//            int indiceSeleccionado = random.nextInt(opciones.length);
//            String opcionSeleccionada = opciones[indiceSeleccionado];
//            salidaResultado.setText(opcionSeleccionada);
//        }
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//    }
//}
//
