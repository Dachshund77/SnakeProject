package Domain.Board;

import Application.MainController;
import Domain.Food.Foods;
import Domain.PlayerEntity.PlayerEntities;
import Domain.Sprite.Sprites;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

/**
 * Interfaces access point for the different BoardModels.
 * A BoardModel is a container that holds all relevant information of the Board like {@link Sprites}.
 * Boardmodels will be attached to a {@link Domain.Game.GameModels}.
 */
public interface BoardModels {

    /**
     * Method that returns all Sprites on a board that can be drawn.
     *<br><br>
     * <font color = RED> NOTE</font> That the Sprites arrayList should never be modified directly, else ConcurrentModificationException are likely.
     * Modification of this arrayList should only done via specified methods. For {@link Domain.Game.GameModels#addSpriteQue(Sprites) adding} and
     * {@link Sprites#setIsRemoved(boolean) removing}.
     * @return All Drawable Sprites on this Board.
     */
    ArrayList<Sprites> getSprites();

    /**
     * Helper method that will return all movablePlaterEnteties.
     * As {@link PlayerEntities#handleUserInput(KeyCode) userInput} will need to be handled repeatedly, having
     * the player entities stored separably will ease performance on the game.
     * @return List of All PlayerEntities
     * @see MainController
     */
    ArrayList<PlayerEntities> getMovablePlayerEntities();

    /**
     * Helper method that returns all Foods.
     * @return All Foods on the Board
     */
    ArrayList<Foods> getFoods();

    /**
     * Helper method that removes a Sprite from the Board. Note that this method will and should also remove all other
     * references in other arrays from this Board.
     * <br><br>
     * <font color=Red>NOTE</font> that this method should not be called directly, but via the gameLoop  {@link Domain.Game.GameModels#updateGameState(long) gameLoop}.
     * Doing this will guarantee that we avoid ConcurrentModificationExceptions.
     * @param s Sprites that will be removed
     * @return True, if successfully removed
     */
    boolean removeSprite(Sprites s);

    /**
     * Helper method that adds a Sprite to the Board. Note that this method will and should also add other
     * references in other arrays to this Board.
     * <br><br>
     * <font color=Red>NOTE</font> that this method should not be called directly, but via the gameLoop  {@link Domain.Game.GameModels#updateGameState(long) gameLoop}.
     * Doing this will guarantee that we avoid ConcurrentModificationExceptions.
     * @param s Sprites that will be added
     * @return True, if successfully added
     */
    boolean addSprite(Sprites s);

    /**
     * Helper method that will get this Board height.
     * @return Height of this board
     */
    double getHeight();

    /**
     * Helper method that will get this Board width.
     * @return Width of this board
     */
    double getWidth();

    /**
     * Helper method that will return a y Coordinate inside the board Area.
     * @return Y Coordinate inside board.
     */
    double getRandomY();

    /**
     * Helper method that will return a x Coordinate inside the board Area.
     * @return x Coordinate inside board.
     */
    double getRandomX();
}
