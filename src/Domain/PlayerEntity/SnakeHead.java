package Domain.PlayerEntity;

import Domain.Food.Foods;
import Domain.Game.GameModels;
import Domain.Sprite.Sprites;
import Domain.Timeable.SnakeBody;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The main entity a player will control. This SnakeHead is also in charge of placing its tail.
 */
public class SnakeHead extends MoveablePlayerEntity { //TODO the circle could become a image or something

    private Color paint;
    private SnakeControl controls;
    private double blockedTimeRemaining;
    private KeyCode nextAction;
    private double bodyLength;

    /**
     * ArrayList to give easy Reference acces to the {@link SnakeBody snakeBodies} that were placed by this snakeHead.
     */
    private ArrayList<SnakeBody> tail;

    public SnakeHead(double xPosition, double yPosition, double height, double width, String name, Color paint, double speed, SnakeControl controls, double bodyLength) {
        super(xPosition, yPosition, height, width, speed, name);
        this.paint = paint;
        this.controls = controls;
        this.bodyLength = bodyLength;
        this.tail = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     * <br><br>
     * In this case the render method will draw a circle.
     */
    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(paint);
        gc.fillOval(xPosition, yPosition, width, height);
    }

    /**
     *{@inheritDoc}
     * <br><br>
     * This method will in order
     * <ul>
     *     <li>Place a new {@link SnakeBody}</li>
     *     <li>Remove timedOut bodies from its {@link #tail}</li>
     *     <li>Move the SnakeHead</li>
     *     <li>Decrement the blocked time if blocked</li>
     * </ul>
     */
    @Override
    public void move(long time, GameModels gameModels) {
        // adding an snakebody to the tail
        if (xVelocity != 0 || yVelocity != 0) {
            double tempxPosition = xPosition;
            double tempyPosition = yPosition;
            double snakeHeadHeight = height;
            double snakeHeadWidth = width;
            double newSnakeBodyHeight = height; //Could technicly be smaller then the snakehead
            double newSnakeBodyWidth = width; //Could technicly be smaller then the snakehead
            double snakeHeadSpeed = speed;
            double bodyLength = this.bodyLength;
            double noCollisionTime = ((newSnakeBodyWidth + snakeHeadWidth) / snakeHeadSpeed) + ((newSnakeBodyHeight + snakeHeadHeight) / snakeHeadSpeed);

            SnakeBody body = new SnakeBody(tempxPosition, tempyPosition, newSnakeBodyHeight, newSnakeBodyWidth, Color.RED, bodyLength, noCollisionTime);
            gameModels.addSpriteQue(body);
            tail.add(body);
        }

        //Cleaning up the tail references

        Iterator<SnakeBody> iterator = tail.iterator();
        while (iterator.hasNext()) {
            SnakeBody snakeBody = iterator.next();
            if (snakeBody.getCurrentLifetime() > snakeBody.getMaxLifeTime()) {
                iterator.remove();
            } else {
                break;
            }
        }

        // Moving the snake
        super.move(time, gameModels);

        // Blocking snake so it does not suicide immediately
        if (blockedTimeRemaining >= 0) {
            blockedTimeRemaining -= time;
        } else if (nextAction != null) {
            handleUserInput(nextAction);
        }
    }

    /**
     *{@inheritDoc}
     * <br><br>
     * In this case the Snake can Move in all Cardinal Directions. If the Snake change direction it will be briefly blocked.
     */
    @Override
    public void handleUserInput(KeyCode keyCode) {
        if (blockedTimeRemaining < 0) { //If its not blocked than do
            if (keyCode.equals(controls.getUp()) && yVelocity == 0) { //Upwards movement
                xVelocity = 0;
                yVelocity = -speed;
                blockedTimeRemaining = height / speed;
            } else if (keyCode.equals(controls.getDown()) && yVelocity == 0) { //Down Movement
                xVelocity = 0;
                yVelocity = speed;
                blockedTimeRemaining = height / speed;
            } else if (keyCode.equals(controls.getLeft()) && xVelocity == 0) { //Left Movement
                xVelocity = -speed;
                yVelocity = 0;
                blockedTimeRemaining = width / speed;
            } else if (keyCode.equals(controls.getRight()) && xVelocity == 0) { //Right Movement
                xVelocity = speed;
                yVelocity = 0;
                blockedTimeRemaining = width / speed;
            }
            nextAction = null;
        } else {
            nextAction = keyCode; // We are storing this to smooth out the user input.
        }
    }

    /**
     * {@inheritDoc}
     * <br><br>
     * In this case the SnakeHead will react on Foods, gaining length and score.
     * Any other collision will result in the death of the SnakeHead.
     */
    @Override
    public void handleCollision(Sprites s, GameModels gameModels) {
        if (s instanceof Foods) {
            Foods f = (Foods) s;
            double addedlegth = f.getAddedLength();
            bodyLength += addedlegth;

            int curretScore = gameModels.scoreProperty().get();
            gameModels.scoreProperty().set((int) (curretScore + f.getScoreValue()));

            for (SnakeBody snakeBody : tail) {
                snakeBody.setMaxLifeTime(snakeBody.getMaxLifeTime()+addedlegth);
            }

            f.setIsRemoved(true);

        } else {
            isRemoved = true;
        }
    }
}
