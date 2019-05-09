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
 * Actual implementation of the Board state.
 */
public class BlankBoard extends BoardModel {


    public BlankBoard(double height, double width) {
        super(height, width);
    }
}


