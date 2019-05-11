package Domain.Food;

import Domain.Sprite.Sprites;
import javafx.geometry.Rectangle2D;

/**
 * Abstract class tol hold shared implementation's for all SimpleFoods.
 */
public abstract class SimpleFoods implements Foods, Sprites {

    //From interfaces Sprites
    protected double xPosition;
    protected double yPosition;
    protected double height;
    protected double width;
    protected boolean isRemoved;

    public SimpleFoods(double xPosition, double yPosition, double height, double width) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.height = height;
        this.width = width;
        this.isRemoved = false;
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(xPosition,yPosition,width,height);    }

    @Override
    public boolean intersects(Sprites s) {
        return s.getBoundary().intersects( this.getBoundary() );
    }

    @Override
    public boolean isRemoved() {
        return isRemoved;
    }

    @Override
    public void setIsRemoved(boolean newStatus) {
        isRemoved = newStatus;
    }

    @Override
    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    @Override
    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }
}
