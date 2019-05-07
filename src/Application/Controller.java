package Application;

import Domain.Board.BasicBoard;
import Domain.Game.BasicGame;
import Domain.Game.GameModels;
import Domain.PlayerEntity.MoveablePlayerEntity;
import Domain.PlayerEntity.PlayerEntities;
import Domain.Sprite.Sprites;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

/**
 * Main Controller for the Snake game. Note that the controller is just the top level and any game or board logic is done by the models.
 *
 * @see GameModels
 * @see Domain.Board.BoardModels
 */
public class Controller {

    @FXML
    public Canvas backGroundCanvas;
    @FXML
    public Canvas gameCanvas;
    @FXML
    public Canvas uiCanvas;
    @FXML
    public Label scoreCountLabel;

    private GameModels gameModel;

    private long lastNanoTime = System.nanoTime();

    /**
     * Method to start and attach a new gameModels and BordModels to to the Scene.
     *
     * @param event the event that starts this method.
     */
    public void handleNewGame(ActionEvent event) {
        gameModel = new BasicGame(new BasicBoard(gameCanvas.getHeight(), gameCanvas.getWidth()));
        startGameLoop();
    }

    /**
     * Method that initializes the gameLoop and runs it
     */
    private void startGameLoop() {
        //Init needed values
        //Get gc for canvases
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) { //TODO possible rewrite this with timelines and keyframes. More control over fps.
                // set needed value to keep track of time pased
                System.out.println("currentNanoTime = " + currentNanoTime);
                long timePassedInMilliseconds = ((currentNanoTime - lastNanoTime) / 1000000);
                System.out.println("timePassedInMilliseconds = " + timePassedInMilliseconds);
                lastNanoTime = currentNanoTime;

                //Update game
                gameModel.updateGameState(timePassedInMilliseconds); // TODO no colission yet

                //Draw on canvas
                GraphicsContext gameCanvasGC = gameCanvas.getGraphicsContext2D();
                gameCanvasGC.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight()); //Whole canvas

                // Draw sprites
                ArrayList<Sprites> sprites = gameModel.getBoardModel().getAllSprites();
                for (Sprites sprite : sprites) {
                    sprite.render(gameCanvasGC); //TODO need a scale for model to canvas scaling... mb
                }
            }
        }.start();
    }


    @FXML
    public void handleUserInput(KeyEvent keyEvent) {
        // Get the need values
        KeyCode keyCode = keyEvent.getCode();

        // For each player entity we look if it does something
        ArrayList<PlayerEntities> playerEntities = gameModel.getBoardModel().getMovablePlayerEntities();
        for (PlayerEntities p : playerEntities) {
            p.handleUserInput(keyCode);
        }

    }
}
