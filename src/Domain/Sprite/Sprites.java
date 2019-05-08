package Domain.Sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Interface for the Sprites in the game. A Sprite can have an image or a shape as visual representation.
 * Each an every Element that can be displayed on the GameBoard should implement this interface.
 */
public interface Sprites {

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

    /**
     * Helper Method that we get the value of the isRemoved flag.
     * This flag need to be raised for the {@link Domain.Game.GameModels#updateGameState(long) updateGamestate} to garbageCollect that Sprite.
     * <br>
     * <font color = RED>NOTE</font> That removing a Sprite directly from the {@link Domain.Board.BoardModels BoardModel} will most
     * likely result in a concurrentModificationException.
     * @return True if Sprite was removed.
     */
    boolean isRemoved();

    /**
     * Helper method to set the isRemoved Flag.
     * @param newStatus True or False
     */
    void setIsRemoved(boolean newStatus);
}
