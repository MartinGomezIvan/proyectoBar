package com.example.proyectohospital;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import domain.Datos.Cita;
import javafx.collections.FXCollections;
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
import org.bson.Document;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ListaEspera implements Initializable {

    @FXML
    private Button botonEliminar;

    @FXML
    private Button botonModificar;
    @FXML
    private Button botonVolver;
    @FXML
    private TableView<Cita> salidaCitas;
    @FXML
    private TextField meterFechaActualizar;

    @FXML
    private TextArea meterMotivoActualizar;
    @FXML
    private ComboBox<String> seleccionarEspecialidadActualizar;

    @FXML
    private ComboBox<String> seleccionarMedicoActualizar;
    private CitaDAO citaDAO;
    private Cita citaSeleccionada;

    String fechaEscogida;
    String motivoEscogido;
    String medicoEscogido;
    String especialidadEscogida;
    public ListaEspera(){
        citaDAO=new CitaDAO();
    }

    @FXML
    void pulsarBotonEliminar(ActionEvent event) {
        try{
            if(citaSeleccionada==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Debe seleccionar una cita para eliminar");

                alert.showAndWait();
            }else{
                citaDAO.eliminarCita(citaSeleccionada);
                cargarDatos();
                fijarColumnasTabla();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void pulsarBotonModificar(ActionEvent event) {//No me lo mdofica la primera vez, solo me añade uno nuevo con la inforación nueva
        try{
            if(citaSeleccionada==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Debe seleccionar una cita para modificar");

                alert.showAndWait();
            }else{
                fechaEscogida=meterFechaActualizar.getText();
                motivoEscogido=meterMotivoActualizar.getText();
                especialidadEscogida=seleccionarEspecialidadActualizar.getValue();
                medicoEscogido=seleccionarMedicoActualizar.getValue();
                citaDAO.actualizarCita(medicoEscogido,especialidadEscogida,fechaEscogida,motivoEscogido, citaSeleccionada);
                cargarDatos();
                fijarColumnasTabla();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void seleccionarCita(MouseEvent event) {
        citaSeleccionada=salidaCitas.getSelectionModel().getSelectedItem();
        cargarCita(citaSeleccionada);
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Información sobre la cita");
//        alert.setContentText("Pulse el botón modificar para modificar su pedido.A continuación complete su nuevo pedido"+"\n"+"Pulse el botón eliminar para eliminar su pedido;Después pulse de nuevo el botón de ver los pedidos para confirmar que se ha elimminado");
//
//        alert.showAndWait();
    }
    public void cargarCita(Cita cita){
        meterFechaActualizar.setText(cita.getFechaCita());
        seleccionarEspecialidadActualizar.setValue(cita.getEspecialidadesCitas());
        seleccionarMedicoActualizar.setValue(cita.getMedicoCita());
        meterMotivoActualizar.setText(cita.getMotivoCita());
        cargarCombobox();
    }

    private void cargarDatos() {
        //salidaCitas.getItems().clear(); Si pongo esto da error
        //List<Cita> coches = citaDAO.obtenerDatos(); Si pongo esto, se me duplican los campos de la tabla
                                                    // debido a que  se me crea otra lista distinta
        //salidaCitas.setItems(FXCollections.observableArrayList(coches).sorted());
        salidaCitas.getItems().setAll(citaDAO.obtenerDatos());
    }
    private void fijarColumnasTabla() {
        salidaCitas.getColumns().clear();//Para qque se mme bore todo lo que tengo en la tabla
        Field[] fields = Cita.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("id"))
                continue;

            TableColumn<Cita, String> column = new TableColumn<>("[" + field.getName() + "]");
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            salidaCitas.getColumns().add(column);
        }

        salidaCitas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    public void cargarCombobox(){
        if(citaSeleccionada==null){

        }else{
            String[] especialidades={"Dermatología","Odontología","Pediatría","Ginecología","Cardiología","Cirugía General","Neurología","Oftalmología"};
            String[] medicos={"Juan Alberto Juez","María Rosa Puentes","Santiago Sánchez","Lidia Rodriguez","Federico Núñez","Sofía Martín","Eugenio Gómez","Agustina Fernández"};
            seleccionarEspecialidadActualizar.setItems(FXCollections.observableArrayList(especialidades));
            seleccionarMedicoActualizar.setItems(FXCollections.observableArrayList(medicos));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        fijarColumnasTabla();
//        cargarDatos();
        if (salidaCitas.getColumns().isEmpty()) { //Aquí comprueba si hay en la tabla datos
            fijarColumnasTabla();//Si no hay datos, asigna los campos, para lugo cargarlos
        }
        cargarDatos();


    }
}
