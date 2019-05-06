package Domain.Board;

import Domain.Sprite.SnakeBody;
import Domain.Sprite.SnakeHead;
import Domain.Sprite.Sprites;

import java.util.ArrayList;

/**
 * Interfaces access point for the different BoardModels
 */
public interface BoardModels {

    /**
     * Updates the Sprites on a board.
     * Note that the This method should be called in the GameLoop.
     * @param milSecPassed Time passed since last update
     * @see Sprites
     */
    void updateBoardState(long milSecPassed);

    /**
     * Method that returns all Sprites on a board that can be drawn.
     * @return All Drawable Sprites on this Board.
     */
    ArrayList<Sprites> getAllSprites();

    /**
     * Method that returns all SnakeBodies on a board.
     * @return List of all Snake Bodies
     */
    ArrayList<SnakeBody> getAllSnakeBodies();

    /**
     * Method that gets this board player object, the snakeHead.
     * @return the players snakeHead
     */
    SnakeHead getSnakeHead();

}
