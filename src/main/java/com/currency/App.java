package com.currency;

import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    private static final String API_URL = "https://open.er-api.com/v6/latest/";
    private static final String API_KEY = "adc48f4fb2364473bf5f672e45fe3679";

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
        
        // Amount input field
        TextField inputField = new TextField();
        inputField.getStyleClass().add("input-field");
        inputField.setPromptText("Enter the amount");
        inputField.setPrefHeight(35);
        textFields.getChildren().add(inputField);

        // Currency input field
        TextField currencyField = new TextField();
        currencyField.getStyleClass().add("input-field");
        currencyField.setPrefHeight(35);
        textFields.getChildren().add(currencyField);

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

        // Exit the application when the window is closed
        stage.setOnCloseRequest(event -> System.exit(0));

        stage.show();
    }

    private static double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        
        return 0.0;
    }
   public static void main(String args[]){
      launch(args);
   }

}