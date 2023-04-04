package com.alura.conversordivisas.controller;

import com.alura.conversordivisas.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ConverterOptionsController {

    FXMLLoader fxmlLoader;
    private Stage stage;
    private Scene scene;

    //Objetos de interfaz
    @FXML
    private ComboBox<String> converterSelection;
    @FXML
    private Button acceptButton;

    //Metodo para manejar lo que sucede cuando hace click en el boton aceptar
    @FXML
    protected void onAcceptButtonClicked(ActionEvent event) {
        String selection = converterSelection.getSelectionModel().getSelectedItem();
        if (Objects.equals(selection, "Conversor de Moneda")) {
            //Se abre la siguiente ventana
            try {
                switchToCurrencyOptionsScene(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //Opcion por implementar
        }
    }

    //Metodo para cambiar a la siguiente ventana
    public void switchToCurrencyOptionsScene(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Application.class.getResource("currency-options-view.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Conversor");
        stage.setScene(scene);
        stage.show();
    }

}