package com.currency;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        // Create the main layout components
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
        insideSection.setSpacing(120);
        section.setCenter(insideSection);

        // Create the Scene
        Scene scene = new Scene(root, 500, 700);

        stage.setScene(scene);
        stage.setTitle("Precision Purse");
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setX(1000);
        stage.setY(40);

        // Title
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
        textFields.getChildren().add(inputField);

        // Currency input field
        TextField currencyField = new TextField();
        currencyField.getStyleClass().add("input-field");
        textFields.getChildren().add(currencyField);

        // Convert button
        Button convertButton = new Button("Convert");
        convertButton.getStyleClass().add("convert-button");
        insideSection.getChildren().add(convertButton);

        // Exit the application when the window is closed
        stage.setOnCloseRequest(event -> System.exit(0));

        stage.show();
    }
   public static void main(String args[]){
      launch(args);
   }

}