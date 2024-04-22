module com.example.cab302homeproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.cab302homeproject to javafx.fxml;
    exports com.example.cab302homeproject;
}