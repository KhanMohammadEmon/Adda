module com.example.adda {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.adda to javafx.fxml;
    exports com.example.adda;
}