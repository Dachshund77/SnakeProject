package Domain.Moveable;

import Domain.PlayerEntity.MovablePlayerEntity;
import Domain.Sprite.Sprites;
import Domain.Timeable.Timeable;
import Domain.Timeable.Timeables;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class SnakeBody extends Timeable { //TODO changes in velocity need to be considered to properly make the snake body collision

    private Color paint;


    public SnakeBody(double xPosition, double yPosition, double height, double width, Color paint, double maxLifeTime, double collisionIgnoranceTime) {
        super(xPosition, yPosition, height, width, maxLifeTime,0,collisionIgnoranceTime);
        this.paint = paint;
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
}
