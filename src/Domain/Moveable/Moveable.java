package Domain.Moveable;

import Domain.Game.GameModels;
import Domain.Sprite.Sprites;
import javafx.geometry.Rectangle2D;

public abstract class Moveable implements Moveables, Sprites {

    // From interface Sprites
    protected double xPosition;
    protected double yPosition;
    protected double height;
    protected double width;
    protected boolean isRemoved;

    //From interface Movables
    protected double xVelocity;
    protected double yVelocity;
    protected double speed;

    public Moveable(double xPosition, double yPosition, double height, double width, double speed) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.height = height;
        this.width = width;
        this.isRemoved = false;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.speed = speed;
    }

    @Override
    public void move(long time, GameModels gameModels) {
        xPosition += xVelocity * time;
        yPosition += yVelocity * time;
    }

    @Override
    public boolean intersects(Sprites s) {
        return s.getBoundary().intersects(this.getBoundary());
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(xPosition, yPosition, width, height);
    }

    @Override
    public boolean isRemoved() {
        return isRemoved;
    }

    @Override
    public void setIsRemoved(boolean newStatus) {
        isRemoved = newStatus;
    }
}
