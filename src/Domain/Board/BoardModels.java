package Domain.Board;

import Domain.Sprite.Sprites;

import java.util.ArrayList;

/**
 * Interfaces access point for the different BoardModels
 */
public interface BoardModels {

    /**
     * Updates the Sprites on a board.
     * Note that the This method should be called in the GameLoop.
     * @param currentNanoTime Time passed since last update
     * @see Sprites
     */
    void updateBoardState(long currentNanoTime);

    /**
     * Method that returns all Sprites on a board that should be drawn.
     * @return All Drawable Sprites on this Board.
     */
    ArrayList<Sprites> getAllSprites();
}
