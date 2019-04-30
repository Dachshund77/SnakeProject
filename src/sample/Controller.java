package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class Controller {

    @FXML
    Label labelScore;
    @FXML
    Button newGame;
    @FXML
    Canvas canvas;


    public void initialize(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeOval(25,25,25,25);
        gc.strokeOval( 25,50,25,25);

    }




    public void handleNewGame(ActionEvent event) {



    }
}
