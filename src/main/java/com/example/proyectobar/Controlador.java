


package com.example.proyectobar;

import com.example.proyectobar.domain.Bar;
import com.example.proyectobar.util.AlertUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class Controlador implements Initializable {

    @FXML
    private Button botonFinalizarPedido;

    @FXML
    private Button botonGuardar;

    @FXML
    private Button botonNuevo;

    @FXML
    private Button botonOpciones;

    @FXML
    private Label lbEstado;

    @FXML
    private ListView<Bar> salidaOpciones;
    @FXML
    private ComboBox<String> seleccionarBebida;

    @FXML
    private ComboBox<String> seleccionarComplemento;

    @FXML
    private ComboBox<String> seleccionarMarca;

    @FXML
    private ComboBox<String> seleccionarMezcla;

    @FXML
    private ComboBox<String> seleccionarSabor;


    @Override// Metodo que se ejecuta una vez se abre la ventana
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[]opciones=new String[]{"Vodka", "Ron","Ginebra","Licor","Whisky"};
        seleccionarBebida.setItems(FXCollections.observableArrayList(opciones));
        //Metodo ChangeListener, para que depende de lo que pulse en el primer combobox,
        //se me actualice la información de los demás combobox.
        seleccionarBebida.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //Aquí se llama al método donde actualizo la inforrmación de los combobox
                actualizarComboBoxs(newValue);
            }
        });

    }
    private void actualizarComboBoxs(String bebidaSeleccionada) {
        //Actualizamos los combobox
        seleccionarMarca.getSelectionModel().clearSelection();
        seleccionarSabor.getSelectionModel().clearSelection();
        seleccionarMezcla.getSelectionModel().clearSelection();
        seleccionarComplemento.getSelectionModel().clearSelection();
        if(bebidaSeleccionada.equals("Vodka")) {
            String[] vodka = new String[]{"Belvedere", "Eristoff","Absolut","Ciroc" };
            seleccionarMarca.setItems(FXCollections.observableArrayList(vodka));
            String[]saborVodka=new String[]{"Fresa","Tamarindo","Sandía","Lima"};
            seleccionarSabor.setItems(FXCollections.observableArrayList(saborVodka));
            String[]mezclaSinLicor=new String[]{"CocaCola", "Naranja", "Limón", "Tónica"};
            seleccionarMezcla.setItems(FXCollections.observableArrayList(mezclaSinLicor));
            String[]complementosSinLicor=new String[]{"Rodaja Limon","Rodaja Naranja", "Gominolas","Hielo","Gotas de anís"};
            seleccionarComplemento.setItems(FXCollections.observableArrayList(complementosSinLicor));
        }else if(bebidaSeleccionada.equals("Ron")) {
            String[] ron=new String[]{"Negrita","Brugal","La Recompensa","Barceló"};
            seleccionarMarca.setItems(FXCollections.observableArrayList(ron));
            String[]saborRon=new String[]{"Añejo","Blanco","Negro","Dorado"};
            seleccionarSabor.setItems(FXCollections.observableArrayList(saborRon));
            String[]mezclaSinLicor=new String[]{"CocaCola", "Naranja", "Limón", "Tónica"};
            seleccionarMezcla.setItems(FXCollections.observableArrayList(mezclaSinLicor));
            String[]complementosSinLicor=new String[]{"Rodaja Limon","Rodaja Naranja", "Gominolas","Hielo","Gotas de anís"};
            seleccionarComplemento.setItems(FXCollections.observableArrayList(complementosSinLicor));
        }
        else if(bebidaSeleccionada.equals("Ginebra")) {
            String[] ginebra=new String[]{"Puerto de Indias","Seagram's","Tanqueray","Beefeater"};
            seleccionarMarca.setItems(FXCollections.observableArrayList(ginebra));
            String[]saborGinebra=new String[]{"Floral","Cítrica","Especiada","Afrutada"};
            seleccionarSabor.setItems(FXCollections.observableArrayList(saborGinebra));
            String[]mezclaSinLicor=new String[]{"CocaCola", "Naranja", "Limón", "Tónica"};
            seleccionarMezcla.setItems(FXCollections.observableArrayList(mezclaSinLicor));
            String[]complementosSinLicor=new String[]{"Rodaja Limon","Rodaja Naranja", "Gominolas","Hielo","Gotas de anís"};
            seleccionarComplemento.setItems(FXCollections.observableArrayList(complementosSinLicor));
        }
        else if(bebidaSeleccionada.equals("Licor")) {
            String[] licor=new String[]{"Cherry Blossom","Cointreau", "Licor 47", "Licor Hierbas"};
            seleccionarMarca.setItems(FXCollections.observableArrayList(licor));
            String[]saborLicor=new String[]{"Manzana","Melocotón","Mora","Cereza"};
            seleccionarSabor.setItems(FXCollections.observableArrayList(saborLicor));
            String[]mezclaconLicor=new String[]{"Nada", "Naranja","CocaCola", "Limón"};
            seleccionarMezcla.setItems(FXCollections.observableArrayList(mezclaconLicor));
            String[]complementosConLicor=new String[]{"Nada","Hielo"};
            seleccionarComplemento.setItems(FXCollections.observableArrayList(complementosConLicor));
        }else if(bebidaSeleccionada.equals("Whisky")) {
            String[] whisky=new String[]{"Burbon","JB","DYC","Cardú"};
            seleccionarMarca.setItems(FXCollections.observableArrayList(whisky));
            String[]saborWhisky=new String[]{"Irlandés","Galés","Canadiense","Estadounidense"};
            seleccionarSabor.setItems(FXCollections.observableArrayList(saborWhisky));
            String[]mezclaSinLicor=new String[]{"CocaCola", "Naranja", "Limón", "Tónica"};
            seleccionarMezcla.setItems(FXCollections.observableArrayList(mezclaSinLicor));
            String[]complementosSinLicor=new String[]{"Rodaja Limon","Rodaja Naranja", "Gominolas","Hielo","Gotas de anís"};
            seleccionarComplemento.setItems(FXCollections.observableArrayList(complementosSinLicor));
        }
    }


    private final ConexionBD conexionBD;
    String marcaBuena="";
    String complementoBueno="";
    String bebidaBuena="";
    String saborBueno="";
    String mezclaBuena="";


    public Controlador() { //Constructor

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


    //Guardamos la información en variables para luego llamar al método de la clase ConexionBD
    @FXML
    void pulsarBotonGuardar(ActionEvent event) throws SQLException {
        if (seleccionarBebida.getValue() == null || seleccionarSabor.getValue() == null || seleccionarMezcla.getValue() == null || seleccionarMarca.getValue() == null || seleccionarComplemento.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en datos");
            alert.setContentText("Debe completar todos los campos");
            alert.showAndWait();
            throw new RuntimeException("");
        } else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AVISO");
            alert.setContentText("Aunque usted cierre la aplicación, sus pedidos no se borrarán"+"\n"+"Cuando haga el pago se borrarán todos sus pedidos");
            alert.showAndWait();

            bebidaBuena=seleccionarBebida.getValue();
            saborBueno=seleccionarSabor.getValue();
            mezclaBuena=seleccionarMezcla.getValue();
            marcaBuena = seleccionarMarca.getValue();
            complementoBueno = seleccionarComplemento.getValue();
            Bar objBar= new Bar(bebidaBuena,saborBueno,mezclaBuena,marcaBuena,complementoBueno);
            conexionBD.guardarBebida(objBar);
        }


    }
    //Vaciamos los combobox
    private void limpiarCajas() {
        seleccionarBebida.setValue("");
        seleccionarSabor.setValue("");
        seleccionarMezcla.setValue("");
        seleccionarMarca.setValue("");
        seleccionarComplemento.setValue("");

    }
    //Pulsamos el botón nuevo y se limpian los  combobox
    @FXML
    void pulsarBotonNuevo(ActionEvent event) {
        limpiarCajas();
    }

    //Pulsamos el botón VerOpciones y se muestran las opciones que otros usuarios han seleccionado
    //para que nos sirva de ayuda a la hora de elegir nuestro pedido
    @FXML
    void pulsarBotonOpciones(ActionEvent event) throws SQLException {
        try {
            salidaOpciones.getItems().clear();
            List<Bar> bebidas = conexionBD.obtenerBebidas();
            salidaOpciones.setItems(FXCollections.observableArrayList(bebidas).sorted());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
//Botón que pulsamso cuando ya hemos acabado de pedir nuestros pedidos
    @FXML
    void pulsarbotonFinalizarPedido(ActionEvent event)throws SQLException {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("pedido.fxml"));
        try {
            Parent root = fxmlLoader.load();
            seleccionarBebida.setValue(null);
            seleccionarSabor.setValue(null);
            seleccionarMezcla.setValue(null);
            seleccionarMarca.setValue(null);
            seleccionarComplemento.setValue(null);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            // Obtener una referencia al Stage de la ventana actual
            Stage currentStage = (Stage) botonFinalizarPedido.getScene().getWindow();
            // Cerrar la ventana actual
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    }





