package com.example.proyectobar;

import com.example.proyectobar.util.AlertUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Pagar {

    @FXML
    private Button botonAceptar;

    @FXML
    private Button botonInicio;

    @FXML
    private TextField ponerCorreo;

    @FXML
    private TextField ponerTelefono;
    String correoBueno="";
    String telefonoBueno="";
    String saberSiHePulsadoElBotonAceptar="";
    private final ConexionBD conexionBD;

    public Pagar(){//Constructor
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
    }

    @FXML
    void pulsarBotonAceptar(ActionEvent event) {//Para que nos envíe la factura si está todo correcto
        String telefono=ponerTelefono.getText();
        System.out.println(telefono.length());
        String correo=ponerCorreo.getText();
        if (ponerCorreo.getText().isEmpty() || ponerTelefono.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en datos");
            alert.setContentText("Debe completar todos los campos");
            alert.showAndWait();
            throw new RuntimeException("");
            //Aquí controlamos que el telefono no sea un String, y de 9 caracteres obligatoriamente
            // Además el correo debe tener una @
        }else if (!telefono.matches("\\d{9}+") || !correo.matches(".*@.*")){
            // Mostrar un mensaje de error si no es un número válido
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en datos");
            alert.setContentText("El teléfono debe ser un número válido. Debe tener 9 dígitos"+"\n"+"El correo debe contener una @");
            alert.showAndWait();
            throw new RuntimeException("");
        }

        else {
            saberSiHePulsadoElBotonAceptar="si";
            correoBueno=ponerCorreo.getText();
            telefonoBueno=ponerTelefono.getText();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Factura");
            alert.setContentText("Se le enviará la factura al correo "+correoBueno+", y la copia de seguridad al "+telefonoBueno);
            alert.showAndWait();
        }
    }

    @FXML
    void pulsarBotonInicio(ActionEvent event) {//Te vuelve al inicio
        if (!saberSiHePulsadoElBotonAceptar.equals("si")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Debe pulsar el botón Aceptar");
            alert.showAndWait();
        }else{
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("menuinicial.fxml"));
            try {
                conexionBD.aniadirPedidosAOpciones();//Te añade tus pedidos a las opciones
                conexionBD.eliminarTodo();//Borra tus pedidos de la base de datos
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                // Obtener una referencia al Stage de la ventana actual
                Stage currentStage = (Stage) botonInicio.getScene().getWindow();
                // Cerrar la ventana actual
                currentStage.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        }


}
