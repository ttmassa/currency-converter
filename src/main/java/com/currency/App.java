package com.currency;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    public void start(Stage stage) {

        Group root = new Group();

        Scene scene = new Scene(root, 500, 700);

        stage.setScene(scene);
        stage.setX(1000);
        stage.setY(40);

        stage.setTitle("Currency Converter");
        

        // Exit the application when the window is closed
        stage.setOnCloseRequest(event -> System.exit(0));

        stage.show();
    }
   public static void main(String args[]){
      launch(args);
   }

}