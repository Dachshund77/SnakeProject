package Domain.Board;

import Domain.Sprite.Food;
import Domain.Sprite.SnakeBody;
import Domain.Sprite.SnakeHead;
import Domain.Sprite.Sprites;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Actual implementation of the Board state. Note that the models height and width can differ from the canvas where it is displayed.
 */
public class BoardModel implements BoardModels { //TODO possible change the fields to take sprite interface

    //Double[][] coordinates; //TODO storing stuff in 2d array might be obsolete
    private double height;
    private double width;
    private ArrayList<Food> foods;

    private SnakeHead snakeHead;
    private ArrayList<SnakeBody> snakeBodies;

    public BoardModel(double height, double width) {
        this.height = height;
        this.width = width;

        this.snakeHead = new SnakeHead(width/2,height/2,height*0.05,width*0.05,Color.RED);

        this.snakeBodies = new ArrayList<>();
        this.foods = new ArrayList<>();
    }

    @Override
    public void updateBoardState(long currentNanoTime) {
        //Move snake head and save for new snake body
        double tempxPosition = snakeHead.getxPosition();
        double tempyPosition = snakeHead.getyPosition();
        snakeHead.update(currentNanoTime);

        //Update snake body
        for (SnakeBody snakeBody : snakeBodies) {
            snakeBody.update(currentNanoTime);
        }

        //Add a new body
        snakeBodies.add(new SnakeBody(tempxPosition, tempyPosition, snakeHead.getHeight(), snakeHead.getWidth(), Color.RED, 100000));
    }

    @Override
    public ArrayList<Sprites> getAllSprites() {
        ArrayList<Sprites> returnArrayList = new ArrayList<>();

        returnArrayList.addAll(foods);
        returnArrayList.addAll(snakeBodies);
        returnArrayList.add(snakeHead);

        return returnArrayList;
    }

    @Override
    public SnakeHead getSnakeHead() {
        return snakeHead;
    }
}
