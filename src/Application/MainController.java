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

    public void initialize() {

    }

    /**
     * Method to start and attach a new gameModels and BoardModels to to the Scene.
     * Will start the gameModels {@link GameModels#startGameLoop()}
     * @param event the event that starts this method.
     */
    public void handleNewStandardGame(ActionEvent event) {
        gameModel = new BasicGame(new BlankBoard(gameCanvas.getHeight(), gameCanvas.getWidth()),gameCanvas);
        scoreCountLabel.textProperty().bind(gameModel.scoreProperty().asString());
        gameModel.startGameLoop();
    }

    /**
     * Method to start and attach a new gameModels and BoardModels to to the Scene.
     * Will start the gameModels {@link GameModels#startGameLoop()}
     * @param event the event that starts this method.
     */
    @FXML
    public void handleNewInsaneGame(ActionEvent event) {
        gameModel = new InsaneGame(new BlankBoard(gameCanvas.getHeight(), gameCanvas.getWidth()),gameCanvas);
        scoreCountLabel.textProperty().bind(gameModel.scoreProperty().asString());
        gameModel.startGameLoop();
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
