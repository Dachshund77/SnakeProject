package Domain.TimeMovable;

import Domain.Moveable.Movable;
import Domain.Timeable.Timeables;

public abstract class TimeMovable extends Movable implements Timeables {//TODO not realy finished implementation


    protected double maxLifeTime;
    protected double currentLifetime;
    protected double collisionIgnoranceTime;

    public TimeMovable(double xPosition, double yPosition, double height, double width, double speed, double maxLifeTime, double currentLifetime, double collisionIgnoranceTime) {
        super(xPosition, yPosition, height, width, speed);
        this.maxLifeTime = maxLifeTime;
        this.currentLifetime = currentLifetime;
        this.collisionIgnoranceTime = collisionIgnoranceTime;
    }

    @Override
    public void update(long time) {
        currentLifetime += time;
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

    public void setCurrentLifetime(double currentLifetime) {
        this.currentLifetime = currentLifetime;
    }

    public double getCollisionIgnoranceTime() {
        return collisionIgnoranceTime;
    }

    public void setCollisionIgnoranceTime(double collisionIgnoranceTime) {
        this.collisionIgnoranceTime = collisionIgnoranceTime;
    }

}
