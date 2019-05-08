package Domain.Game;

import Domain.Board.BoardModels;
import Domain.Food.Food;
import Domain.PlayerEntity.SnakeHead;
import Domain.Sprite.Sprites;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Actual implementation of the GameModels interface.
 * This method keep track of score and if the game is over.
 */
public class BasicGame extends GameModel { //TODO this class might also be in charge of playing sounds?

    private IntegerProperty score = new SimpleIntegerProperty(this,"Score",0);

    public BasicGame(BoardModels boardModel) {
        this.boardModel = boardModel; //TODO here we need to make new
        this.score.set(0);
        spawnNextFood();
    }


    public IntegerProperty scoreProperty() {
        return score;
    }


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
