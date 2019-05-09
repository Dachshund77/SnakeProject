package Domain.Game;

import Domain.Board.BoardModels;
import Domain.Food.Food;
import Domain.PlayerEntity.SnakeControl;
import Domain.PlayerEntity.SnakeHead;
import Domain.Sound.SoundPlayer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.Objects;

/**
 * Actual implementation of the GameModels interface.
 * This class keep track of score. And places a single snakeHead in the middle
 */
public class BasicGame extends GameModel { //TODO this class might also be in charge of playing sounds?

    private IntegerProperty score = new SimpleIntegerProperty(this,"Score",0);

    public BasicGame(BoardModels boardModel) {
        this.boardModel = boardModel;
        this.score.set(0);
        //Places the first food
        spawnNextFood();

        //Places the snake in the middle
        SnakeHead snakeHead = new SnakeHead(boardModel.getWidth() / 2, boardModel.getHeight() / 2, 10, 10, "Player 1", Color.RED, 0.1,
                new SnakeControl(KeyCode.UP, KeyCode.LEFT, KeyCode.DOWN, KeyCode.RIGHT),1000);

        addSpriteQue(snakeHead);

        //Starting background music
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("BackgroundMusic/TechnoBackground.mp3")).getFile());
        SoundPlayer.playBackGroundMusic(file);
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    /**
     * {@inheritDoc}
     * <br><br>
     * This implementation will always keep one {@link Food} on the Board.
     */
    @Override
    public void spawnNextFood() {
        if (boardModel.getFoods().size() < 1) {
            double x = boardModel.getRandomX();
            double y = boardModel.getRandomY();

            Food food = new Food(x, y, 10, 10, Color.GREEN, 100, 500);
            addSpriteQue(food);
        }
    }
}
