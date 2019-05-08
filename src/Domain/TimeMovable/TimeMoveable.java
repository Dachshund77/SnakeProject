package Domain.TimeMovable;

import Domain.Game.GameModels;
import Domain.Moveable.Moveables;
import Domain.Sprite.Sprites;
import Domain.Timeable.Timeables;
import javafx.geometry.Rectangle2D;

public abstract class TimeMoveable implements Timeables, Moveables, Sprites {//TODO not realy finished implementation

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

    // From interface Timeables
    protected double maxLifeTime;
    protected double currentLifetime;
    protected double collisionIgnoranceTime;

    public TimeMoveable(double xPosition, double yPosition, double height, double width, double speed, double maxLifeTime, double currentLifetime, double collisionIgnoranceTime) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.height = height;
        this.width = width;
        this.isRemoved = false;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.speed = speed;
        this.maxLifeTime = maxLifeTime;
        this.currentLifetime = currentLifetime;
        this.collisionIgnoranceTime = collisionIgnoranceTime;
    }

    @Override
    public void update(long milSecPassed, GameModels gameModels) {
        currentLifetime += milSecPassed;
    }

    public double getMaxLifeTime() {
        return maxLifeTime;
    }

    public void setMaxLifeTime(double maxLifeTime) {
        this.maxLifeTime = maxLifeTime;
    }

    public double getCurrentLifetime() {
        return currentLifetime;
    }

    @Override
    public void move(long time, GameModels gameModels) {
        xPosition += xVelocity * time;
        yPosition += yVelocity * time;
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(xPosition, yPosition, width, height);
    }

    @Override
    public boolean intersects(Sprites s) {
        return s.getBoundary().intersects(this.getBoundary());
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
