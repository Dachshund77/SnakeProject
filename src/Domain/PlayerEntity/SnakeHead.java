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

    public SnakeHead(double xPosition, double yPosition, double height, double width, String name, Color paint, double speed, SnakeControl controls) {
        super(xPosition, yPosition, height, width, speed, name);
        this.paint = paint;
        this.controls = controls;
    }

    /**
     * {@inheritDoc}
     * In this case the render method will draw a circle.
     */
    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(paint);
        gc.fillOval(xPosition, yPosition, width, height);
    }

    @Override
    public void handleUserInput(KeyCode keyCode) {
        if (keyCode.equals(controls.getUp())) {
            xVelocity = 0;
            yVelocity = -speed;
        } else if (keyCode.equals(controls.getDown())) {
            xVelocity = 0;
            yVelocity = speed;
        } else if (keyCode.equals(controls.getLeft())) {
            xVelocity = -speed;
            yVelocity = 0;
        } else if (keyCode.equals(controls.getRight())) {
            xVelocity = speed;
            yVelocity = 0;
        }
    }

    @Override
    public void handleCollision(Sprites s, GameModels gameModels) {
        if (s instanceof Foods){
            System.out.println("FOUND FOOOOOOD"); //TODO propper implentation
        } else {
            System.out.println("GAAAAAAAAAME OOOOOOOOOVER");
        }
    }
}
