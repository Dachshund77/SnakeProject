package Domain.Moveable;

import Domain.Game.GameModels;
import Domain.Sprite.Sprite;

public abstract class Moveable extends Sprite implements Moveables{

    protected double xVelocity;
    protected double yVelocity;
    protected double speed;

    public Moveable(double xPosition, double yPosition, double height, double width, double speed) {
        super(xPosition, yPosition, height, width);
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.speed = speed;
    }

    @Override
    public void move(long time, GameModels gameModels) {
        xPosition += xVelocity * time;
        yPosition += yVelocity * time;
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
