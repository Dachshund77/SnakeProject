package Domain.Sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Simple wall sprite that is just a rectangle.
 * Can be used to dra the Edges of the Board.
 */
public class Wall extends Sprite {

    private Color paint;

    public Wall(double xPosition, double yPosition, double height, double width, Color paint) {
        super(xPosition, yPosition, height, width);
        this.paint = paint;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(paint);
        gc.fillRect(xPosition,yPosition,width,height);
    }

}
