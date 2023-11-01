package com.example.proyectobar;

import com.example.proyectobar.util.AlertUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;

public class Menuinicial {

    @FXML
    private RadioButton boton18;

    @FXML
    private Button botonEntrar;

    @FXML
    void pulsarBotonEntrar(ActionEvent event) {
        if(!boton18.isSelected()){
            AlertUtils.mostrarError("Debes tener 18 años");
        }else{
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("bar.fxml"));
            try {
                Parent root = fxmlLoader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                // Obtener una referencia al Stage de la ventana actual
                Stage currentStage = (Stage) botonEntrar.getScene().getWindow();
                // Cerrar la ventana actual
                currentStage.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        }
    }


