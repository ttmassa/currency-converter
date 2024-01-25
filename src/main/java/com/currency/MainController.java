package com.currency;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private Button clickMeButton;

    @FXML
    private void handleButtonClick() {
        System.out.println("Button clicked!");
    }

    public void setButtonText(String text) {
        clickMeButton.setText(text);
    }
}

