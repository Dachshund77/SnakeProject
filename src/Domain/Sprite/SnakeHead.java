package Domain.Sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnakeHead extends Sprite { //TODO the circle could become a image or something

    private Color paint; //TODO Snakedhead could ahve a general velocity field
    private double speed; //used to move xy axis, dont realy need x and y velocity specificly

    public SnakeHead(double xPosition, double yPosition, double height, double width, Color paint, double speed) {
        super(xPosition, yPosition, height, width);
        this.paint = paint;
        this.speed = speed;
    }

    /**
     * {@inheritDoc}
     * In this case the render method will draw a circle.
     */
    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(paint);
        gc.fillOval(xPosition, yPosition,width,height);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
