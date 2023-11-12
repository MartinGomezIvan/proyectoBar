module com.example.peliculasjson {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;

    opens com.example.peliculasjson to javafx.fxml;
    exports com.example.peliculasjson;
}