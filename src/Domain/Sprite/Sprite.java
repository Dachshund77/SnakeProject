package Domain.Sprite;

import javafx.geometry.Rectangle2D;

/**
 * Abstract class to hold common implementation for all Sprites.
 */
public abstract class Sprite implements Sprites{

    protected double xPosition;
    protected double yPosition;

    protected double height;
    protected double width;

    public Sprite(double xPosition, double yPosition, double height, double width) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.height = height;
        this.width = width;
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(xPosition,yPosition,width,height);
    }

    @Override
    public boolean intersects(Sprites s) {
        return s.getBoundary().intersects( this.getBoundary() );
    }

    public double getxPosition() {
        return xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

}