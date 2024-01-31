package com.currency;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * JavaFX App
 */
public class App extends Application {

    private static final String API_URL = "https://api.freecurrencyapi.com/v1/latest";
    private static final String API_KEY = "fca_live_myauxWbv97YDmGfrcAq0Kc3Cgh5CHLGuflvMcMhM";
    private static final String[] currencies = {"EUR", "USD", "GBP"};

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        VBox header = new VBox();
        header.getStyleClass().add("header");
        header.setAlignment(Pos.CENTER);
        root.setTop(header);

        BorderPane section = new BorderPane();
        section.getStyleClass().add("section");
        root.setCenter(section);

        VBox insideSection = new VBox();
        insideSection.getStyleClass().add("inside-section");
        insideSection.setAlignment(Pos.CENTER);
        insideSection.setSpacing(75);
        section.setCenter(insideSection);

        Scene scene = new Scene(root, 500, 700);

        stage.setScene(scene);
        stage.setTitle("Precision Purse");
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setX(1000);
        stage.setY(40);

        Text title = new Text("Precision Purse");
        title.getStyleClass().add("title");
        title.setFill(Color.WHITE);
        header.getChildren().add(title);

        // Create the input text fields
        HBox textFields = new HBox();
        textFields.getStyleClass().add("text-fields");
        textFields.setAlignment(Pos.CENTER);
        textFields.setSpacing(80);
        insideSection.getChildren().add(textFields);

        HBox inputSelection = new HBox();
        inputSelection.getStyleClass().add("input-selection");
        inputSelection.setAlignment(Pos.CENTER);
        textFields.getChildren().add(inputSelection);

        HBox resultSelection = new HBox();
        resultSelection.getStyleClass().add("result-selection");
        resultSelection.setAlignment(Pos.CENTER);
        textFields.getChildren().add(resultSelection);
        
        // Amount input field
        TextField inputField = new TextField();
        inputField.getStyleClass().add("input-field");
        inputField.setPromptText("Enter the amount");
        inputField.setPrefHeight(35);
        inputSelection.getChildren().add(inputField);

        // Currency input field
        TextField resultField = new TextField();
        resultField.getStyleClass().add("input-field");
        resultField.setPrefHeight(35);
        resultSelection.getChildren().add(resultField);

        ObservableList<String> currencies = FXCollections.observableArrayList("AUD", "BGN", "BRL", "CAD", "CHF", "EUR", "GBP","USD");

        ComboBox<String> currencyComboBox = new ComboBox<>(currencies);
        currencyComboBox.getStyleClass().add("combo-box");
        currencyComboBox.setPrefHeight(33.5);
        currencyComboBox.setPrefWidth(100);
        currencyComboBox.setValue("EUR");
        inputSelection.getChildren().add(currencyComboBox);

        ComboBox<String> resultComboBox = new ComboBox<>(currencies);
        resultComboBox.getStyleClass().add("combo-box");
        resultComboBox.setPrefHeight(33.5);
        resultComboBox.setPrefWidth(100);
        resultComboBox.setValue("EUR");
        resultSelection.getChildren().add(resultComboBox);

        currencyComboBox.setOnAction(e -> {
            String selectedOption = currencyComboBox.getValue();

            currencyComboBox.setValue(selectedOption);
        });

        // Format the input field to only accept numbers
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getText();
            if (newText.matches("[0-9]*([.,][0-9]*)?")) {
                return change;
            }
            return null;
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        inputField.setTextFormatter(textFormatter);

        Button convertButton = new Button("Convert");
        convertButton.getStyleClass().add("convert-button");
        insideSection.getChildren().add(convertButton);

        convertButton.setOnAction(e -> {
            double amount = Double.parseDouble(inputField.getText());
            String currency = currencyComboBox.getValue();
            String toCurrency = resultComboBox.getValue();
            
            resultField.setText("" + convertCurrency(currency, toCurrency, amount));
        });

        // Exit the application when the window is closed
        stage.setOnCloseRequest(event -> System.exit(0));

        stage.show();
    }

    private static double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        try {
            String apiUrl = API_URL + "?apikey=" + API_KEY + "&currencies=" + toCurrency + "&base_currency=" + fromCurrency;

            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String responseBody = reader.lines().collect(Collectors.joining());

                double exchangeRate = getExchangeRateFromJson(responseBody);

                double convertedAmount = Math.round((amount * exchangeRate) * 100.0) / 100.0;

                connection.disconnect();

                return convertedAmount;
            } else {
                System.out.println("Error: " + responseCode);
                return 0.0;
            }
 
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return 0.0;
        }
        
    }

    private static double getExchangeRateFromJson(String json) {
        try {
            int index = json.indexOf("data");
            String rates = json.substring(index + 7, json.length() - 1);

            int index2 = rates.indexOf(":");

            String exchangeRate = rates.substring(index2 + 1, rates.length() - 1);

            return Double.parseDouble(exchangeRate);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return 0.0;                   
        }
    }
   public static void main(String args[]){
      launch(args);
   }

}