package Domain.Game;

import Domain.Board.BoardModels;
import Domain.Food.Food;
import Domain.PlayerEntity.SnakeControl;
import Domain.PlayerEntity.SnakeHead;
import Domain.Sound.SoundPlayer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;
import java.util.Objects;
import java.util.Random;

/**
 * Actual implementation of the GameModels interface.
 * This class keep track of score. And places a single snakeHead in the middle.
 */
public class InsaneGame extends GameModel { //TODO check entire javaDocs for this class

    private IntegerProperty score = new SimpleIntegerProperty(this,"Score",0);

    public InsaneGame(BoardModels boardModel, Canvas gameCanvas) {
        super(boardModel, gameCanvas);
        //Places the first food
        spawnNextFood();

        //Places the snake in the middle
        SnakeHead snakeHead = new SnakeHead(boardModel.getWidth() / 2, boardModel.getHeight() / 2, 10, 10, "Player 1", Color.RED, 0.1,
                new SnakeControl(KeyCode.UP, KeyCode.LEFT, KeyCode.DOWN, KeyCode.RIGHT),1000);

        addSpriteQue(snakeHead);

        //Starting background music
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("BackgroundMusic/TechnoBackground.mp3")).getFile());
        SoundPlayer.getInstance().playBackGroundMusic(file);
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    /**
     * {@inheritDoc}
     * <br><br>
     * This implementation will always keep one {@link Food} on the Board.
     * Also will rotate the canvas with a 10% chance.
     */
    @Override
    public void spawnNextFood() {
        if (boardModel.getFoods().size() < 1) {
            Food food = new Food(0, 0, 10, 10, Color.GREEN, 100, 500);

            do {
                food.setxPosition(boardModel.getRandomX());
                food.setyPosition(boardModel.getRandomY());

            } while (boardModel.isColliding(food)); //Do while colliding

            Random random = new Random();
            if (random.nextDouble() < 0.1){
                rotateCanvas();
            }

            addSpriteQue(food);


        }
    }

    /**
     * Rotates the gameCanvas by 90 degree
     */
    private void rotateCanvas(){
        double oldRotation = gameCanvas.rotateProperty().get();
        double newRotation = (oldRotation+90)%360;

        //Make a brief animation
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(gameCanvas.rotateProperty(),newRotation))
        );
        timeline.play();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("SoundEffects/Confusion8Bit.wav")).getFile());
        SoundPlayer.getInstance().playSoundEffect(file);
    }
}

