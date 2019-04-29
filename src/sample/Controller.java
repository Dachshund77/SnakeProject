package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.awt.*;

public class Controller {

    @FXML
    Label labelScore;
    @FXML
    Button newGame;
    @FXML
    Canvas canvas;

    public Canvas getCanvas() {
        return canvas;
    }

    public void handleNewGame(ActionEvent event) {



    }
}
