package com.example.proyectobar;
import com.example.proyectobar.Controlador;
import com.example.proyectobar.util.AlertUtils;
import domain.Bar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class Opciones {

    @FXML
    private Button botonVolver;

    @FXML
    private Label lbEstado;

    @FXML
    private ListView<String> salidaDatos;

    public void exportar(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            File fichero = fileChooser.showSaveDialog(null);

            FileWriter fileWriter = new FileWriter(fichero);
            CSVPrinter printer = new CSVPrinter(fileWriter,
                    CSVFormat.DEFAULT.withHeader("id", "matricula", "marca", "modelo", "tipo"));

            List<Bar> bares = conexion.obtenerCoches();
            for (Bar bar : bares)
                printer.printRecord(bar.getId(), coche.getMatricula(), coche.getMarca(),
                        coche.getModelo(), coche.getTipo());

            printer.close();
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("Error al conectar con la base de datos");
        } catch (IOException ioe) {
            AlertUtils.mostrarError("Error al exportar los datos");
        }
    }
    @FXML
    void pulsarBotonVolver(ActionEvent event) {

    }

}
