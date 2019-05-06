package Domain.Game;

import Domain.Board.BoardModels;
import Domain.Sprite.SnakeHead;
import Domain.Sprite.Sprites;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

/**
 * Actual iimplementation of the GameModels interface.
 * This method keep track of score and if the game is over.
 */
public class GameModel implements GameModels { //TODO this class might also be in charge of playing sounds?

    private BoardModels boardModel;
    private IntegerProperty score = new SimpleIntegerProperty(this,"Score",0);

    public GameModel(BoardModels boardModel) {
        this.boardModel = boardModel; //TODO here we need to make new
        this.setScore(0);
    }

    @Override
    public void updateGameState(long milSecPassed) {
        //update board
        boardModel.updateBoardState(milSecPassed);
        //get any collision with the head
        SnakeHead snakeHead = boardModel.getSnakeHead(); //TODO Hardcoded, refactor to be more flexible
        ArrayList<Sprites> sprites = boardModel.getAllSprites();
        for (Sprites sprite : sprites) {
            if (snakeHead.intersects(sprite)){
                System.out.println("Snakehead intersects with "+sprites.getClass());
            }
        }
    }

    @Override
    public BoardModels getBoardModel() {
        return boardModel;
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
