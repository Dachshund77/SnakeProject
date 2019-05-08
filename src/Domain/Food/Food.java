package Domain.Food;

import Domain.Sprite.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Food extends Sprite implements Foods{  //TODO the circle could become a image or something

    private Color paint;
    private double scoreValue;
    private double addedlength;

    public Food(double xPosition, double yPosition, double height, double width, Color paint, double scoreValue, double addedlength) {
        super(xPosition, yPosition, height, width);
        this.paint = paint;
        this.scoreValue = scoreValue;
        this.addedlength = addedlength;
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

    @Override
    public double getScoreValue() {
        return scoreValue;
    }

    @Override
    public double getAddedLength() {
        return addedlength;
    }
}
