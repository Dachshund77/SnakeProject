package Domain.Moveable;

import Domain.Sprite.Sprite;

public abstract class Movable extends Sprite {

    protected double xVelocity;
    protected double yVelocity;
    protected double speed;

    public Movable(double xPosition, double yPosition, double height, double width, double speed) {
        super(xPosition, yPosition, height, width);
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.speed = speed;
    }


    public void move(long time) {
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
