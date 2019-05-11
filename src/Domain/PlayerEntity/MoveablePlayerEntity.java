package Domain.PlayerEntity;

import Domain.Game.GameModels;
import Domain.Moveable.Moveable;
import Domain.Moveable.Moveables;
import Domain.Sprite.Sprites;
import javafx.geometry.Rectangle2D;

/**
 * Abstract class with shared implementations for all MovablesPlayerEntities
 */
public abstract class MoveablePlayerEntity implements PlayerEntities, Moveables, Sprites {//TODO need javaDoc

    //From PlayerEnteties interface
    String name;

    //From interface Movables
    protected double xVelocity;
    protected double yVelocity;
    protected double speed;

    // From interface Sprites
    protected double xPosition;
    protected double yPosition;
    protected double height;
    protected double width;
    protected boolean isRemoved;

    public MoveablePlayerEntity(double xPosition, double yPosition, double height, double width, double speed, String name) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.height = height;
        this.width = width;
        this.isRemoved = false;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.speed = speed;
        this.name = name;
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

    @Override
    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    @Override
    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }
}
