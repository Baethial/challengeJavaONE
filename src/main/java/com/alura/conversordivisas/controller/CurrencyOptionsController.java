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
import java.util.HashMap;
import java.util.Map;

public class CurrencyOptionsController {

    FXMLLoader fxmlLoader;
    private Stage stage;
    private Scene scene;

    //Objetos de interfaz
    @FXML
    private ComboBox<String> conversionTypeSelection;
    @FXML
    private ComboBox<String> currencySelection;
    @FXML
    private Button goBackButton;
    @FXML
    private Button acceptButton;

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

    //Metodo para cambiar de ventana
    public void switchScene(ActionEvent event) throws IOException {

        //Definimos la siguiente ventana a abrirse dependiendo del boton seleccionado
        String nextView;
        if (event.getSource().equals(goBackButton)) {
            nextView = "converter-options-view.fxml";
        } else {
            nextView = "input-value-view.fxml";
        }

        fxmlLoader = new FXMLLoader(Application.class.getResource(nextView));
        scene = new Scene(fxmlLoader.load());
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        if (!event.getSource().equals(goBackButton)) {
            //Capturamos las selecciones de los ComboBox
            int conversionType = conversionTypeSelection.getSelectionModel().getSelectedIndex();
            int currency = currencySelection.getSelectionModel().getSelectedIndex();
            //Encapsulamos la informacion
            Map<String, String> data = new HashMap<>();
            data.put("Type", conversionType==0?"To COP":"From COP");
            String currencyCode = "";
            switch (currency) {
                case 0:
                    currencyCode = "USD";
                    break;
                case 1:
                    currencyCode = "EUR";
                    break;
                case 2:
                    currencyCode = "GBR";
                    break;
                case 3:
                    currencyCode = "JPY";
                    break;
                case 4:
                    currencyCode = "KRW";
                    break;
            }
            data.put("Currency", currencyCode);
            //Anexamos la informacion
            stage.setUserData(data);
        }
        stage.setTitle("Conversor");
        stage.setScene(scene);
        stage.show();
    }

}
