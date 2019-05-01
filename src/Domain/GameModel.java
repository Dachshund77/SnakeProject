package Domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameModel implements GameModels {
    BoardModels boardModel;
    IntegerProperty score = new SimpleIntegerProperty(this,"Score",0);

    public GameModel() {
        this.boardModel = boardModel; //TODO here we need to make new
        this.setScore(0);
    }

    @Override
    public void updateGameState(long currentNanoTime) {
        //update board

        //get any collision with the head
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
