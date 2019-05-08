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

    private GameModels gameModel; // Game Logic that contains the logic interaction

    private long lastNanoTime = System.nanoTime(); //Needed to calculate the time since last update

    /**
     * Method to start and attach a new gameModels and BoardModels to to the Scene.
     *
     * @param event the event that starts this method.
     */
    public void handleNewGame(ActionEvent event) {
        gameModel = new BasicGame(new BasicBoard(gameCanvas.getHeight(), gameCanvas.getWidth()));
        scoreCountLabel.textProperty().bind(gameModel.scoreProperty().asString());
        startGameLoop();
    }

    /**
     * Method that initializes the gameLoop and runs it. Most notably this method will call {@link GameModels#updateGameState(long)}.
     * This method will be in charge of drawing the actual Sprites on the canvas.
     * @see Sprites
     */
    private void startGameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                // ask if the game has ended
                if (gameModel.isGameOver()){
                    stop();
                }

                // set needed value to keep track of time passed
                long timePassedInMilliseconds = ((currentNanoTime - lastNanoTime) / 1000000);
                lastNanoTime = currentNanoTime;

                //Update game, here the game logic will advance
                gameModel.updateGameState(timePassedInMilliseconds);

                //Clear the canvas of everything
                GraphicsContext gameCanvasGC = gameCanvas.getGraphicsContext2D();
                gameCanvasGC.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight()); //Whole canvas

                // Draw sprites
                ArrayList<Sprites> sprites = gameModel.getBoardModel().getSprites();
                for (Sprites sprite : sprites) {
                    sprite.render(gameCanvasGC); //TODO need a scale for model to canvas scaling... mb
                }
            }
        }.start();
    }


    /**
     * Method that will register and notify the playerEntities.
     * Note that it is the entities responsibility to implement a reaction on this method.
     * @param keyEvent The key pressed.
     * @see PlayerEntities
     * @see MoveablePlayerEntity
     */
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
