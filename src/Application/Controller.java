package Application;

import Domain.Board.BoardModel;
import Domain.Game.GameModel;
import Domain.Game.GameModels;
import Domain.Sprite.SnakeHead;
import Domain.Sprite.Sprites;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

/**
 * Main Controller for the Snake game. Note that the controller is just the top level and any game or board logic is done by the models.
 * @see GameModels
 * @see Domain.Board.BoardModels
 */
public class Controller {

    @FXML public Canvas backGroundCanvas;
    @FXML public Canvas gameCanvas;
    @FXML public Canvas uiCanvas;
    @FXML public Label scoreCountLabel;

    private GameModels gameModel;
    private KeyCode lastKeyPressed;

    private long lastNanoTime = System.nanoTime();

    /**
     * Method to start and attach a new gameModels and BordModels to to the Scene.
     * @param event the event that starts this method.
     */
    public void handleNewGame(ActionEvent event) {
        gameModel = new GameModel(new BoardModel(gameCanvas.getHeight(),gameCanvas.getWidth()));
        startGameLoop();
    }

    /**
     * Method that initializes the gameLoop
     */
    private void startGameLoop(){
        System.out.println("Starting new Game Loop"); //TODO Remove before release
        //Init needed values
        //Get gc for canvases
        new AnimationTimer(){
            @Override
            public void handle(long currentNanoTime) { //TODO possible rewrite this with timelines and keyframes. More control over fps.
                System.out.println("currentNanoTime = " + currentNanoTime);
                long timePassedInMilliseconds = ((currentNanoTime - lastNanoTime) / 1000000);
                System.out.println("timePassedInMilliseconds = " + timePassedInMilliseconds);
                lastNanoTime = currentNanoTime;
                //Update elements
                gameModel.updateGameState(timePassedInMilliseconds); // TODO no colission yet
                //Draw
                GraphicsContext gameCanvasGC = gameCanvas.getGraphicsContext2D();
                gameCanvasGC.clearRect(0,0,gameCanvas.getWidth(),gameCanvas.getHeight()); //Whole canvas

                ArrayList<Sprites> spritesToDraw = gameModel.getBoardModel().getAllSprites();
                for (Sprites sprite : spritesToDraw) {
                    sprite.render(gameCanvasGC); //TODO need a scale for model to canvas scaling... mb
                }
            }
        }.start();
    }


    @FXML
    public void handleUserInput(KeyEvent keyEvent) {
        System.out.println("fire event!");
        SnakeHead snakeHead = gameModel.getBoardModel().getSnakeHead(); //TODO I dont like that we have a direct reference to the snakeHead, not very modular
        double speed = 0.1; //TODO should be moved to the snakeHead
        KeyCode keyPressed = keyEvent.getCode();
        switch (keyPressed){
            case UP:
                lastKeyPressed = KeyCode.UP;
                snakeHead.setxVelocity(0);
                snakeHead.setyVelocity(-1*speed);
                break;
            case LEFT:
                lastKeyPressed = KeyCode.LEFT;
                snakeHead.setxVelocity(-1*speed);
                snakeHead.setyVelocity(0);
                break;
            case DOWN:
                lastKeyPressed = KeyCode.DOWN;
                snakeHead.setxVelocity(0);
                snakeHead.setyVelocity(1*speed);
                break;
            case RIGHT:
                lastKeyPressed = KeyCode.RIGHT;
                snakeHead.setxVelocity(1*speed);
                snakeHead.setyVelocity(0);
                break;
        }
        System.out.println("lastKeyPressed = " + lastKeyPressed); //TODO remove before release
    }
}
