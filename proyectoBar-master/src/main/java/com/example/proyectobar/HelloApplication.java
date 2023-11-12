package com.example.proyectobar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.proyectobar.util.R;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void init() throws Exception {
        super.init();
    }
    @Override
    public void start(Stage stage) throws IOException {
        try{
            HelloApplication controller = new HelloApplication();

            FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(R.getUI("/ui/bar.fxml")
            loader.setLocation(getClass().getResource("/ui/bar.fxml"));
            loader.setController(controller);
            VBox vbox = loader.load();

            //controller.cargarDatos();

            Scene scene = new Scene(vbox);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}