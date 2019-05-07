package Domain.Game;

import Domain.Board.BoardModels;
import Domain.PlayerEntity.SnakeHead;
import Domain.Sprite.Sprites;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

/**
 * Actual implementation of the GameModels interface.
 * This method keep track of score and if the game is over.
 */
public class BasicGame extends GameModel { //TODO this class might also be in charge of playing sounds?

    private IntegerProperty score = new SimpleIntegerProperty(this,"Score",0);

    public BasicGame(BoardModels boardModel) {
        this.boardModel = boardModel; //TODO here we need to make new
        this.setScore(0);
        spawnNextFood();
    }

    public int getScore() {
        return score.get();
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    public void setScore(int score) {
        this.score.set(score);
    }
}
