package com.example.proyectohospital;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import domain.Datos.Cita;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.bson.Document;

import java.net.URL;
import java.util.ResourceBundle;

public class PedirCita implements Initializable {

    @FXML
    private Button botonConfirmar;

    @FXML
    private Button botonNuevo;
    @FXML
    private Button botonVolver;


    @FXML
    private TextArea escribirMotivo;

    @FXML
    private TextField ponerDiaMesAño;

    @FXML
    private TextField ponerTitular;

    @FXML
    private ComboBox<String> seleccionarEspecialidad;

    @FXML
    private ComboBox<String> seleccionarMedico;
    private CitaDAO citaDAO;

    String titular="";
    String fecha="";
    String especialidad="";
    String medico="";
    String motivo="";

    public PedirCita(){
        citaDAO= new CitaDAO();
    }

    @FXML
    void pulsarBotonConfirmar(ActionEvent event) {
        try{
            if(ponerTitular.getText()==null || ponerDiaMesAño.getText()==null || seleccionarEspecialidad.getValue()==null|| seleccionarMedico.getValue()==null || escribirMotivo.getText()==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error en datos");
                alert.setContentText("Debe completar todos los campos");
                alert.showAndWait();
            }else{
                titular=ponerTitular.getText();
                fecha=ponerDiaMesAño.getText();
                especialidad=seleccionarEspecialidad.getValue();
                medico=seleccionarMedico.getValue();
                motivo=escribirMotivo.getText();
                Cita cita= new Cita(titular,fecha,especialidad,medico,motivo);
                citaDAO.insertarDatos(cita);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Citas");
                alert.setContentText("Se ha guardado su cita con éxito");
                alert.showAndWait();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void pulsarBotonNuevo(ActionEvent event) {
        ponerTitular.setText("");
        ponerDiaMesAño.setText("");
        seleccionarEspecialidad.setValue("");
        seleccionarMedico.setValue("");
        escribirMotivo.setText("");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] especialidades={"Dermatología","Odontología","Pediatría","Ginecología","Cardiología","Cirugía General","Neurología","Oftalmología"};
        String[] medicos={"Juan Alberto Juez","María Rosa Puentes","Santiago Sánchez","Lidia Rodriguez","Federico Núñez","Sofía Martín","Eugenio Gómez","Agustina Fernández"};
        seleccionarEspecialidad.setItems(FXCollections.observableArrayList(especialidades));
        seleccionarMedico.setItems(FXCollections.observableArrayList(medicos));
    }
}
