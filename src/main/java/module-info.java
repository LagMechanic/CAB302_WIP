module com.zenbrowser.a1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.zenbrowser.a1 to javafx.fxml;
    exports com.zenbrowser.a1;
}