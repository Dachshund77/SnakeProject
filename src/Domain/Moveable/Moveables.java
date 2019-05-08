package Domain.Moveable;

import Domain.Game.GameModels;
import Domain.Sprite.Sprites;

/**
 * Interface acces for all Sprites that can move on the Board.
 */
public interface Moveables extends Sprites{

    /**
     * Method that moves the Sprite on the {@link Domain.Board.BoardModels BoardModel}. This method will be called
     * repeatedly from the {@link GameModels#updateGameState(long) mainGameLoop}.
     * @param time Milliseconds passed since last move.
     * @param gameModels The gameModel calling this method.
     * @see GameModels
     */
    void move(long time, GameModels gameModels);

    /**
     * Method that determines how a Movable should react on a collision. It is up to the actual class to implement how it react on a {@link Sprites sprite}
     * and how it interacts with the {@link GameModels gameModel}. This method will mostly be called from {@link GameModels}.
     * @param s Sprites what is colliding with this Moveables
     * @param gameModels The GameModel calling this method.
     */
    void handleCollision(Sprites s, GameModels gameModels);
}
