package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;


public class Controller {

    @FXML
    Label labelScore;
    @FXML
    Button newGame;
    @FXML
    Canvas canvas;
    public int posX;
    public int posY;


    public void initialize(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeOval(25,25,25,25);
        gc.strokeOval( posX,posY,25,25);



    }



    public void playerPosition(){
        this.posX = 25;
        this.posY = 50;

    }









    public void handleNewGame(ActionEvent event) {



    }
}
