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

public class SnakeHead extends MoveablePlayerEntity { //TODO the circle could become a image or something

    private Color paint;
    private SnakeControl controls;
    private double blockedTimeRemaining;
    private KeyCode nextAction;
    private double bodyLength;
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
     * In this case the render method will draw a circle.
     */
    @Override
    public void render(GraphicsContext gc) { //TODO snakeHead should be in charge of placing bodies
        gc.setFill(paint);
        gc.fillOval(xPosition, yPosition, width, height);
    }

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

            SnakeBody body = new SnakeBody(tempxPosition, tempyPosition, newSnakeBodyHeight, newSnakeBodyWidth, Color.RED, bodyLength, 10000);
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

    @Override
    public void handleUserInput(KeyCode keyCode) {
        if (blockedTimeRemaining < 0) {
            if (keyCode.equals(controls.getUp()) && yVelocity == 0) {
                xVelocity = 0;
                yVelocity = -speed;
                blockedTimeRemaining = height / speed;
            } else if (keyCode.equals(controls.getDown()) && yVelocity == 0) {
                xVelocity = 0;
                yVelocity = speed;
                blockedTimeRemaining = height / speed;
            } else if (keyCode.equals(controls.getLeft()) && xVelocity == 0) {
                xVelocity = -speed;
                yVelocity = 0;
                blockedTimeRemaining = width / speed;
            } else if (keyCode.equals(controls.getRight()) && xVelocity == 0) {
                xVelocity = speed;
                yVelocity = 0;
                blockedTimeRemaining = width / speed;
            }
            nextAction = null;
        } else {
            nextAction = keyCode;
        }
    }

    @Override
    public void handleCollision(Sprites s, GameModels gameModels) {
        if (s instanceof Foods) {
            Foods f = (Foods) s;
            bodyLength += f.getAddedLength();
            int curretScore = gameModels.scoreProperty().get();
            gameModels.scoreProperty().set((int) (curretScore + f.getScoreValue()));
            f.setIsRemoved(true);
        } else {
            isRemoved = true;
        }
    }
}
