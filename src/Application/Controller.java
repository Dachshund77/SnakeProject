package Application;

import Domain.BoardModel;
import Domain.GameModel;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;


public class Controller {

    @FXML public Canvas backGroundCanvas; //TODO if everything is redrawn anyway, whats the point of different layers
    @FXML public Canvas foodCanvas;
    @FXML public Canvas snakeCanvas;
    @FXML public Label scoreCountLabel;

    private GameModel gameModel;

    //TODO potential cleanup candidates
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
        gameModel = new GameModel();
    }

    private void startGameLoop(){
        //Init needed values

        //Get gc for canvases
        new AnimationTimer(){
            @Override
            public void handle(long now) {
                //Update elements

                //Detect collision

                //Draw
            }
        }.start();
    }
}
