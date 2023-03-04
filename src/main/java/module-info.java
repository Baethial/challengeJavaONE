module com.alura.conversordivisas {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.alura.conversordivisas to javafx.fxml;
    exports com.alura.conversordivisas;
}