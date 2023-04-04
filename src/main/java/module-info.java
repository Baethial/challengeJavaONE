module com.alura.conversordivisas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    exports com.alura.conversordivisas.model to com.google.gson;
    opens com.alura.conversordivisas to com.google.gson, javafx.fxml;
    exports com.alura.conversordivisas;
    exports com.alura.conversordivisas.controller;
    opens com.alura.conversordivisas.controller to javafx.fxml;
}