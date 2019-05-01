package Domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameModel {
    BoardModel boardModel;
    IntegerProperty scoreProperty = new SimpleIntegerProperty(this,"Score",0);

    public GameModel() {
        this.boardModel = boardModel; //TODO here we need to make new
        this.setScoreProperty(0);
    }

    public int getScoreProperty() { //TODO naming convention question, mb call this just getScore
        return scoreProperty.get();
    }

    public IntegerProperty scorePropertyProperty() { //TODO naming convention
        return scoreProperty;
    }

    public void setScoreProperty(int scoreProperty) {
        this.scoreProperty.set(scoreProperty);
    }
}
