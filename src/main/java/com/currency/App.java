package com.currency;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        StackPane root = new StackPane();
        root.getStyleClass().add("root"); 

        VBox main = new VBox();
        main.getStyleClass().add("main");
        main.setAlignment(Pos.CENTER);

        HBox header = new HBox();
        header.getStyleClass().add("header");
        header.setAlignment(Pos.CENTER);
        main.getChildren().add(header);

        HBox section = new HBox();
        section.getStyleClass().add("section");
        section.setAlignment(Pos.CENTER);
        main.getChildren().add(section);

        Scene scene = new Scene(root, 500, 700);

        stage.setScene(scene);
        stage.setTitle("Precision Purse");
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setX(1000);
        stage.setY(40);

        Text title = new Text("Precision Purse");
        title.getStyleClass().add("title");
        header.getChildren().add(title);

        Button convertButton = new Button("Convert");
        section.getChildren().add(convertButton);

        main.getChildren().add(new Region());

        root.getChildren().add(main);

        // Exit the application when the window is closed
        stage.setOnCloseRequest(event -> System.exit(0));

        stage.show();
    }
   public static void main(String args[]){
      launch(args);
   }

}