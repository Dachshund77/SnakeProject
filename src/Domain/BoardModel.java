package Domain;

import java.util.ArrayList;

public class BoardModel implements BoardModels{

    Double[][] coordinates;
    ArrayList<Food> foods;

    SnakeHead snakeHead;
    ArrayList<SnakeBody> snakeBodies;

    public BoardModel() { //TODO need implementation
    }

    @Override
    public void updateBoardState(long currentNanoTime) {
        //Move snake head

        //Update snake body
    }
}
