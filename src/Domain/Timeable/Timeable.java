package Domain.Timeable;

import Domain.Sprite.Sprite;

public abstract class Timeable extends Sprite implements Timeables {

    protected double maxLifeTime;
    protected double currentLifetime;
    protected double collisionIgnoranceTime;

    public Timeable(double xPosition, double yPosition, double height, double width, double maxLifeTime, double currentLifetime, double collisionIgnoranceTime) {
        super(xPosition, yPosition, height, width);
        this.maxLifeTime = maxLifeTime;
        this.currentLifetime = currentLifetime;
        this.collisionIgnoranceTime = collisionIgnoranceTime;
    }

    @Override
    public void update(long time) {
        currentLifetime += time;
        if (collisionIgnoranceTime > 0) {
            collisionIgnoranceTime -= time;
        }
    }

    public double getMaxLifeTime() {
        return maxLifeTime;
    }

    public double getCurrentLifetime() {
        return currentLifetime;
    }

}
