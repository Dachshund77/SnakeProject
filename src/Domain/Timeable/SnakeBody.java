package Domain.Timeable;

import Domain.Game.GameModels;
import Domain.Sprite.Sprites;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Snakebody class that the 'Tail' of a snake consist of.
 * @see Domain.PlayerEntity.SnakeHead
 */
public class SnakeBody extends Timeable { //TODO changes in velocity need to be considered to properly make the snake body collision

    private Color paint;


    public SnakeBody(double xPosition, double yPosition, double height, double width, Color paint, double maxLifeTime, double collisionIgnoranceTime) {
        super(xPosition, yPosition, height, width, maxLifeTime,0,collisionIgnoranceTime);
        this.paint = paint;
    }


    /**
     * {@inheritDoc}
     * <br><br>
     * In this case it will draw an Oval.
     */
    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(paint);
        gc.fillOval(xPosition,yPosition,width,height);
    }

    /**
     * {@inheritDoc}
     * <br><br>
     * This implementation will also return false if the collisionIgnoranceTime is bigger than the current lifetime.
     * Will briefly ignore collision after creation.
     * @return True if sprites overlapping, false if not or condition is not met.
     */
    @Override
    public boolean intersects(Sprites s) {
        if (collisionIgnoranceTime > 0){
            return false;
        } else {
            return super.intersects(s);
        }
    }

    /**
     * {@inheritDoc}
     * <br><br>
     * This implementation will remove this Snakebody if the currentLifeTime is bigger than MaxLifetime.
     */
    @Override
    public void update(long milSecPassed, GameModels gameModels) {
        super.update(milSecPassed, gameModels);
        if (collisionIgnoranceTime < 0){ //TODO just for testin, should be REMOVED
            paint = Color.GREEN;
        }
        if (currentLifetime > maxLifeTime){
            isRemoved = true;
        }
    }

}
