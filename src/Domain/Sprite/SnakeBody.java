package Domain.Sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnakeBody extends Sprite {

    private Color paint;
    private double maxLifeTime;
    private double currentLifetime;

    public SnakeBody(double xPosition, double yPosition, double height, double width, Color paint, double maxLifeTime) {
        super(xPosition, yPosition, height, width);
        this.paint = paint;
        this.currentLifetime = 0;
        this.maxLifeTime = maxLifeTime;
    }

    /**
     * {@inheritDoc}
     * In this object we will also increment the currentLifetime of this object.
     */
    @Override
    public void update(double time) { //TODO were not actually removing anything from the boardModel
        super.update(time);
        currentLifetime += time;
    }

    /**
     * {@inheritDoc}
     * In this case it will draw an Oval.
     */
    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(paint);
        gc.fillOval(xPosition,yPosition,width,height);
    }

    public double getCurrentLifetime() {
        return currentLifetime;
    }

    public double getMaxLifeTime() {
        return maxLifeTime;
    }

    public void setMaxLifeTime(double maxLifeTime) {
        this.maxLifeTime = maxLifeTime;
    }
}
