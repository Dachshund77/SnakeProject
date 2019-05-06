package Domain.Sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnakeBody extends Sprite { //TODO changes in velocity need to be considered to properly make the snake body collision

    private Color paint;
    private double maxLifeTime; //TODO that can be changed into a property and bind to the gameModel i think
    private double currentLifetime;
    private double collisionIgnoranceTime;

    public SnakeBody(double xPosition, double yPosition, double height, double width, Color paint, double maxLifeTime, double collisionIgnoranceTime) {
        super(xPosition, yPosition, height, width);
        this.paint = paint;
        this.currentLifetime = 0;
        this.maxLifeTime = maxLifeTime;
        this.collisionIgnoranceTime = collisionIgnoranceTime;
    }

    /**
     * {@inheritDoc}
     * In this object we will also increment the currentLifetime of this object.
     */
    @Override
    public void update(double time) { //TODO were not actually removing anything from the boardModel
        super.update(time);
        currentLifetime += time;
    }

    /**
     * {@inheritDoc}
     * In this case it will draw an Oval.
     */
    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(paint);
        gc.fillOval(xPosition,yPosition,width,height);
    }

    /**
     * {@inheritDoc}
     * This implementation will also return false if the collisionIgnoranceTime is bigger or equal that the current lifetime.
     * @return True if sprites overlapping, false if not or condition is not met.
     */
    @Override
    public boolean intersects(Sprites s) {
        if (collisionIgnoranceTime >= currentLifetime){
            return false;
        } else {
            return super.intersects(s);
        }
    }

    public double getCurrentLifetime() {
        return currentLifetime;
    }

    public double getMaxLifeTime() {
        return maxLifeTime;
    }

    public void setMaxLifeTime(double maxLifeTime) {
        this.maxLifeTime = maxLifeTime;
    }

    public double getCollisionIgnoranceTime() {
        return collisionIgnoranceTime;
    }

    public void setCollisionIgnoranceTime(double collisionIgnoranceTime) {
        this.collisionIgnoranceTime = collisionIgnoranceTime;
    }
}
