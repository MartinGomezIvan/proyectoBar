module com.example.proyectohospital {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires mongo.java.driver;
    requires com.google.gson;

    opens com.example.proyectohospital to javafx.fxml;
    opens domain.Datos to javafx.base, com.google.gson;
    exports com.example.proyectohospital;
}