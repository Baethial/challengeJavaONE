package com.alura.conversordivisas.controller;

import com.alura.conversordivisas.Application;
import com.alura.conversordivisas.model.CurrencyConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class InputValueController {

    FXMLLoader fxmlLoader;
    private Stage stage;
    private Scene scene;

    @FXML
    TextField valueTextField;
    @FXML
    TextField resultTextField;
    @FXML
    Button convertButton;
    @FXML
    Button goBackButton;
    @FXML
    Label helpText;

    //Atributos
    CurrencyConverter converter;

    //Metodos

    //Metodo para manejar lo que sucede cuando hace click en alguno de los botones
    @FXML
    protected void onButtonClicked(ActionEvent event){

        //Se abre la siguiente ventana
        try {
            switchScene(event);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void switchScene(ActionEvent event) throws IOException {

        //Definimos la ventana anterior a abrirse si se selecciono el boton volver
        if (event.getSource().equals(goBackButton)) {
            fxmlLoader = new FXMLLoader(Application.class.getResource("currency-options-view.fxml"));
            scene = new Scene(fxmlLoader.load());
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Conversor");
            stage.setScene(scene);
            stage.show();
        }

        if (event.getSource().equals(convertButton)) {
            convert(event);
        }

    }

    public void convert(ActionEvent event) {

        //Obtener datos de la seleccion del usuario
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        HashMap<String, String> data = (HashMap<String, String>) stage.getUserData();
        String conversionType = data.get("Type");
        String currency = data.get("Currency");

        //Crear objeto convertidor
        converter = new CurrencyConverter();
        String result;

        // Validacion del dato ingresado por el usuario
        double inputValue;
        try {
            inputValue = Double.parseDouble(valueTextField.getText());
            //Conversion
            if (conversionType.equals("From COP")) {
                result = String.format("%.2f",converter.convert(inputValue, "COP", currency)) + " " + currency;
            } else {
                result = String.format("%.2f", converter.convert(inputValue, currency, "COP")) + " COP";
            }
            displayResult(result);
        } catch (NumberFormatException e) {
            helpText.setVisible(true);
            resultTextField.setText("");
            e.printStackTrace();
        }

    }
    public void displayResult(String outputText) {
        resultTextField.setText(outputText);
    }


}
