package dforensics.dji.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    Button button;

    @FXML
    Text text;

    @FXML
    public void buttonClicked(){
        text.setText("Button Clicked!!!");
    }
}
