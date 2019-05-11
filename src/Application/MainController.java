package Application;

import Domain.Board.BlankBoard;
import Domain.Game.BasicGame;
import Domain.Game.GameModels;
import Domain.Game.InsaneGame;
import Domain.PlayerEntity.MoveablePlayerEntity;
import Domain.PlayerEntity.PlayerEntities;
import Domain.Sprite.Sprites;
import UI.SoundOptions;
import javafx.animation.AnimationTimer;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Main MainController for the Snake game. Note that the controller is just the top level and any game or board logic is done by the models.
 *
 * @see GameModels
 * @see Domain.Board.BoardModels
 */
public class MainController {

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

    public void initialize() {

    }

    /**
     * Method to start and attach a new gameModels and BoardModels to to the Scene.
     *
     * @param event the event that starts this method.
     */
    public void handleNewStandardGame(ActionEvent event) {
        gameModel = new BasicGame(new BlankBoard(gameCanvas.getHeight(), gameCanvas.getWidth()));
        scoreCountLabel.textProperty().bind(gameModel.scoreProperty().asString());
        startGameLoop();
    }

    /**
     * Method to start and attach a new gameModels and BoardModels to to the Scene.
     *
     * @param event the event that starts this method.
     */
    @FXML
    public void handleNewInsaneGame(ActionEvent event) {
        gameModel = new InsaneGame(new BlankBoard(gameCanvas.getHeight(), gameCanvas.getWidth()));
        scoreCountLabel.textProperty().bind(gameModel.scoreProperty().asString());
        startGameLoop();
    }

    /**
     * Method that initializes the gameLoop and runs it. Most notably this method will call {@link GameModels#updateGameState(long)}.
     * This method will be in charge of drawing the actual Sprites on the canvas.
     *
     * @see Sprites
     */
    private void startGameLoop() {
        //TODO it would be pretty cool if the game would pause if we ever click on something that is not the game area
        //mainBorderPane.getCenter().focusedProperty().addListener();
/*
        Parent parent = backGroundCanvas.getScene().getRoot();
        parent.getScene().getWindow().focusedProperty();
        parent.getScene().getWindow().focusedProperty().addListener(
                (prop, oldNode, newNode) -> {
                    System.out.println("prop = " + prop);
                    System.out.println("oldNode = " + oldNode);
                    System.out.println("newNode = " + newNode);
                    System.out.println(parent.getScene().getFocusOwner());
                    System.out.println(parent.getScene().focusOwnerProperty().toString());
                    System.out.println(parent.getScene().getWindow().focusedProperty().get());
                    System.out.println("----------------");
                });
*/
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                // ask if the game has ended
                if (gameModel.isGameOver()) {
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
     *
     * @param keyEvent The key pressed.
     * @see PlayerEntities
     * @see MoveablePlayerEntity
     */
    @FXML
    public void handleUserInput(KeyEvent keyEvent) {
        if (gameModel!=null) {
            // Get the need values
            KeyCode keyCode = keyEvent.getCode();

            // For each player entity we look if it does something
            ArrayList<PlayerEntities> playerEntities = gameModel.getBoardModel().getMovablePlayerEntities();
            for (PlayerEntities p : playerEntities) {
                p.handleUserInput(keyCode);
            }
        }

    }

    @FXML
    public void handleSoundOption(ActionEvent actionEvent) throws IOException {
        SoundOptions soundOptions = new SoundOptions();
        soundOptions.start(new Stage());
    }


}
