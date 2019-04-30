package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;


public class Controller {

    @FXML public Canvas backGroundCanvas;
    @FXML public Canvas foodCanvas;
    @FXML public Canvas snakeCanvas;
    @FXML
    Label scoreCountLabel;
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
