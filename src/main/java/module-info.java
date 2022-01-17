module com.example.adda {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.adda to javafx.fxml;
    exports com.example.adda;
}