package Domain.Sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

/**
 * Interface for the Sprites in the game. A Sprite can have an image or a shape as visual representation.
 */
public interface Sprites {

    /**
     * Method that will update relevant fields after amount time has passed.
     * @param time double of how much time passed //TODO miliseconds, seconds?
     */
    void update(double time);

    /**
     * Method that tells the GraphicsContext how to draw this object.
     * @param gc the Canvases GraphicsContext
     */
    void render(GraphicsContext gc);

    /**
     * Returns a rectangle to determine collisions.
     * @return a 2D Rectangle in the same size as he object
     */
    Rectangle2D getBoundary();

    /**
     * Method to determine if two Sprites are overlapping.
     * @param s The other Sprite.
     * @return true if the Sprites are overlapping
     */
    boolean intersects(Sprites s);
}
