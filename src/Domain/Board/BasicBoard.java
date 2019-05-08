package Domain.Board;

import Domain.Food.Food;
import Domain.Food.Foods;
import Domain.Moveable.Moveables;
import Domain.PlayerEntity.PlayerEntities;
import Domain.PlayerEntity.SnakeControl;
import Domain.Sprite.Sprites;
import Domain.Sprite.Wall;
import Domain.TimeMovable.TimeMoveable;
import Domain.Timeable.SnakeBody;
import Domain.PlayerEntity.SnakeHead;
import Domain.Timeable.Timeables;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Actual implementation of the Board state. //TODO improve docs
 */
public class BasicBoard extends BoardModel {


    public BasicBoard(double height, double width) {
        super(height, width);

        SnakeHead snakeHead = new SnakeHead(width / 2, height / 2, 10, 10, "Player 1", Color.RED, 0.1,
                new SnakeControl(KeyCode.UP, KeyCode.LEFT, KeyCode.DOWN, KeyCode.RIGHT),1000);

        addSprite(snakeHead);

    }
}


