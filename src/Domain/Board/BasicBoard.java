package Domain.Board;

import Domain.Food.Food;
import Domain.Timeable.SnakeBody;
import Domain.PlayerEntity.SnakeHead;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Actual implementation of the Board state. //TODO improve docs
 */
public class BasicBoard extends BoardModel {


    private ArrayList<Food> foods;

    public BasicBoard(double height, double width) {
        this.height = height;
        this.width = width;

        this.snakeHead = new SnakeHead(width/2,height/2,height*0.05,width*0.05,Color.RED, 0.1);

        this.snakeBodies = new ArrayList<>();
        this.foods = new ArrayList<>();
    }

    @Override
    public void updateBoardState(long milSecPassed) {//TODO needs moving
        //Move snake head and save for new snake body
        double tempxPosition = snakeHead.getxPosition();
        double tempyPosition = snakeHead.getyPosition();
        snakeHead.update(milSecPassed);

        //Update snake body, Need to use a iterator to avoid ConcurrentModificationException
        for (Iterator<SnakeBody> iterator = snakeBodies.iterator();iterator.hasNext();){
            SnakeBody snakeBody = iterator.next();
            snakeBody.move(milSecPassed);
            if (snakeBody.getCurrentLifetime() > snakeBody.getMaxLifeTime()){
                iterator.remove();
            }
        }

    }


}
