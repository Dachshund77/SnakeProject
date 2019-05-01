package Domain.Sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Food extends Sprite {  //TODO the circle could become a image or something

    private Color paint;

    public Food(double xPosition, double yPosition, double height, double width, Color paint) {
        super(xPosition, yPosition, height, width);
        this.paint = paint;
    }

    /**
     * {@inheritDoc}
     * In this case the render will draw a rectangle
     */
    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(paint);
        gc.fillRect(xPosition,yPosition,width,height);
    }
}
