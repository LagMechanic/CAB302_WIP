module com.zenbrowser.a {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.junit.jupiter.api;
    requires java.desktop;


    opens com.zenbrowser.a1 to javafx.fxml;
    exports com.zenbrowser.a1;
}