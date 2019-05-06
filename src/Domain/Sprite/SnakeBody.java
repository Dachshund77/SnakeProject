package Domain.Sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnakeBody extends Sprite {

    private Color paint;
    private double lifetime;

    public SnakeBody(double xPosition, double yPosition, double height, double width, Color paint, double lifetime) {
        super(xPosition, yPosition, height, width);
        this.paint = paint;
        this.lifetime = lifetime;
    }

    /**
     * {@inheritDoc}
     * In this object we will also increment the lifetime of this object.
     */
    @Override
    public void update(double time) { //TODO were not actually removing anything from the boardModel
        super.update(time);
        lifetime += time;
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

    public double getLifetime() {
        return lifetime;
    }
}
