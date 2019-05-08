package Domain.TimeMovable;

import Domain.Game.GameModels;
import Domain.Moveable.Moveable;
import Domain.Timeable.Timeables;

public abstract class TimeMoveable extends Moveable implements Timeables {//TODO not realy finished implementation


    protected double maxLifeTime;
    protected double currentLifetime;
    protected double collisionIgnoranceTime;

    public TimeMoveable(double xPosition, double yPosition, double height, double width, double speed, double maxLifeTime, double currentLifetime, double collisionIgnoranceTime) {
        super(xPosition, yPosition, height, width, speed);
        this.maxLifeTime = maxLifeTime;
        this.currentLifetime = currentLifetime;
        this.collisionIgnoranceTime = collisionIgnoranceTime;
    }

    @Override
    public void update(long time, GameModels gameModels) {
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
