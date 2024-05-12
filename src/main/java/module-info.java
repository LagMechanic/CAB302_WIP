module com.zenbrowser.a1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.web;
    requires static lombok;




    opens com.zenbrowser.a1 to javafx.fxml;
    exports com.zenbrowser.a1;
    exports com.zenbrowser.a1.Controller;
    opens com.zenbrowser.a1.Controller to javafx.fxml;
}