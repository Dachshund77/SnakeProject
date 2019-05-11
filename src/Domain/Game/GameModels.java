package Domain.Game;

import Application.MainController;
import Domain.Board.BoardModels;
import Domain.Sprite.Sprites;
import javafx.beans.property.IntegerProperty;

/**
 * Interface access for different gameModels. The gameModels are where the overall game logic will be handled.
 * To function a GameModel must hold at least:
 * <ul>
 *     <li>A References to a {@link BoardModels} accessible via {@link #getBoardModel()}.</li>
 *     <li>A flag (field) to store if the game has ended </li>
 *     <ul>
 *         <li> This flag is accessible via {@link #isGameOver()}</li>
 *         <li> This flag is set via {@link #detectGameEnd()}</li>
 *     </ul>
 *     <li>A ArrayList addQue accesible via {@link #addSpriteQue(Sprites)}</li>
 *     <li>An implemntation of the {@link #updateGameState(long)} method</li>
 * </ul>
 */
public interface GameModels {

    /**
     * Method that updates the GameState and detect eventual important gameStates like GameOver.
     * Note that this method should move and update each entity according to their implementations.
     * This method will be repeatedly called from the MainController class.
     * @param milSecPassed Milliseconds passed since last call.
     * @see MainController
     */
    void updateGameState(long milSecPassed);

    /**
     * Helper method that returns the Board attached to the Game.
     * @return Board for this game.
     */
    BoardModels getBoardModel();

    /**
     * This method handel's the logic how and what kind of food will be spawned in the Game.
     */
    void spawnNextFood();

    /**
     * This method handel's the logic when a game ends.
     * Note that this method should also be responsible to raise the isEnded field.
     * @see #isGameOver()
     */
    void detectGameEnd();

    /**
     * Helper method to access the isEnded field. This fields is a flag that tells the caller if the game has ended or not.
     * @return True, if the game is over.
     * @see #detectGameEnd()
     */
    boolean isGameOver();

    /**
     * Helper method to access the score property.
     * @return IntegerProperty of the score
     */
    IntegerProperty scoreProperty();

    /**
     * Adds a Sprites object to the addQue. This que will add any {@link Sprites Sprites} to the {@link BoardModels} when the gameLoop is in the correct state.
     * <br><br>
     * <font color=RED>NOTE</font> that this method should be called whenever a Sprites should be added to the game.
     * Failing this a ConcurrentModificationException is very Likely.
     * @param s The Sprites that should be added
     * @return True, if the Sprites was added
     * @see Sprites
     * @see BoardModels
     * @see GameModel#addSpriteQue(Sprites)
     */
    boolean addSpriteQue(Sprites s);

    /**
     * Method that initializes the gameLoop and runs it. Most notably this method will call {@link GameModels#updateGameState(long)}.
     * This method will be in charge of drawing the actual Sprites on the canvas.
     *
     * @see Sprites
     */
    void startGameLoop();
}
