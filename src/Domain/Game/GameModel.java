package Domain.Game;

import Domain.Board.BoardModels;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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
    public void updateGameState(long currentNanoTime) {
        //update board
        boardModel.updateBoardState(currentNanoTime);

        //get any collision with the head
    }

    @Override
    public BoardModels getBoardModel() {
        return boardModel;
    }

    public int getScore() { //TODO naming convention question, mb call this just getScore
        return score.get();
    }

    public IntegerProperty scoreProperty() { //TODO naming convention
        return score;
    }

    public void setScore(int score) {
        this.score.set(score);
    }
}
