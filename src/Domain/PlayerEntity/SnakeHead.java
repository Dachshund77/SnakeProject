package Domain.PlayerEntity;

import Domain.Food.Foods;
import Domain.Game.GameModels;
import Domain.Sprite.Sprites;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class SnakeHead extends MoveablePlayerEntity { //TODO the circle could become a image or something

    private Color paint;
    private SnakeControl controls;
    private double blockedTimeRemaining;
    private KeyCode nextAction;
    private double bodyLength;

    public SnakeHead(double xPosition, double yPosition, double height, double width, String name, Color paint, double speed, SnakeControl controls, double bodyLength) {
        super(xPosition, yPosition, height, width, speed, name);
        this.paint = paint;
        this.controls = controls;
        this.bodyLength = bodyLength;
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
    public void setPaint(Color paint) {
        System.out.println("JUST FORTESTING");
    }

    @Override
    public void move(long time) {
        super.move(time);
        if (blockedTimeRemaining >= 0) {
            blockedTimeRemaining -= time;
        }else if(nextAction != null) {
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

    public double getBodyLength() {
        return bodyLength;
    }

    @Override
    public void handleCollision(Sprites s, GameModels gameModels) {
        if (s instanceof Foods) {
            Foods f = (Foods) s;
            bodyLength += f.getAddedLength();
            int curretScore = gameModels.scoreProperty().get();
            gameModels.scoreProperty().set((int) (curretScore+f.getScoreValue()));
            gameModels.getBoardModel().removeFood(f);
            gameModels.spawnNextFood();
        } else {
            gameModels.handleGameEnd();
        }
    }
}
