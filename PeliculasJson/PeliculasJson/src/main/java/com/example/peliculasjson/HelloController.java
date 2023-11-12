package com.example.peliculasjson;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.input.MouseEvent;

public class HelloController {

    @FXML
    private Button botonImportar;

    @FXML
    private TextField ponerDirector;

    @FXML
    private TextField ponerFecha;

    @FXML
    private TextField ponerGenero;

    @FXML
    private TextField ponerTitulo;

    @FXML
    private ListView<Pelicula> salidaPeliculas;
    private Pelicula tituloSeleccionado;

    public static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    @FXML
    void pulsarBotonImportar(ActionEvent event) {
        try{
            ArrayList<Pelicula> peliculas =JSON_MAPPER.readValue(new File("src/main/resources/peliculas.json"),
                    JSON_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, Pelicula.class));

            for (int i=0; i<peliculas.size(); i++){
                String sacarTitulo=(peliculas.get(i).getTitulo()+" ");
                System.out.println();
                salidaPeliculas.setItems(FXCollections.observableArrayList(peliculas));
                //System.out.println(p);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void pulsarTitulo(MouseEvent event) {
        tituloSeleccionado=salidaPeliculas.getSelectionModel().getSelectedItem();
        cargarPelicula(tituloSeleccionado);
    }
    private void cargarPelicula(Pelicula pelicula){
        String sacarFecha= pelicula.getFecha();
        String sacarTitulo=pelicula.getTitulo();
        String sacarGenero=pelicula.getGenero();
        String sacarDirector=pelicula.getDirector();
        ponerTitulo.setText(sacarTitulo);
        ponerDirector.setText(sacarDirector);
        ponerGenero.setText(sacarGenero);
        ponerFecha.setText(sacarFecha);

    }


}
