package Domain.Timeable;

import Domain.Game.GameModels;
import Domain.Sprite.Sprites;
import javafx.geometry.Rectangle2D;

/**
 * Abstract class with shared implementations for all Timeables.
 * @see Timeables
 */
public abstract class Timeable implements Timeables, Sprites {

    // From interface Sprites
    protected double xPosition;
    protected double yPosition;
    protected double height;
    protected double width;
    protected boolean isRemoved;

    //From interface Timeables
    protected double maxLifeTime;
    protected double currentLifetime;
    protected double collisionIgnoranceTime;

    public Timeable(double xPosition, double yPosition, double height, double width, double maxLifeTime, double currentLifetime, double collisionIgnoranceTime) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.height = height;
        this.width = width;
        this.isRemoved = false;
        this.maxLifeTime = maxLifeTime;
        this.currentLifetime = currentLifetime;
        this.collisionIgnoranceTime = collisionIgnoranceTime;
    }

    @Override
    public void update(long milSecPassed, GameModels gameModels) {
        currentLifetime += milSecPassed;
        if (collisionIgnoranceTime >= 0) {
            collisionIgnoranceTime -= milSecPassed;
        }
    }

    public double getMaxLifeTime() {
        return maxLifeTime;
    }

    public double getCurrentLifetime() {
        return currentLifetime;
    }

    @Override
    public void setMaxLifeTime(double newMaxLifeTime) {
        maxLifeTime = newMaxLifeTime;
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(xPosition,yPosition,width,height);
    }

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
