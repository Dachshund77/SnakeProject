package Domain;

import javafx.geometry.Rectangle2D;

public abstract class Sprite implements Sprites{

    double xPosition;
    double yPosition;

    private double xVelocity;
    private double yVelocity;

    double height;
    double width;

    public Sprite(double xPosition, double yPosition, double height, double width) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.height = height;
        this.width = width;
    }

    @Override
    public void update(double time) {
        xPosition += xVelocity * time;
        yPosition += yVelocity * time;
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(xPosition,yPosition,width,height);
    }

    @Override
    public boolean intersects(Sprite s) {
        return s.getBoundary().intersects( this.getBoundary() );
    }
}
