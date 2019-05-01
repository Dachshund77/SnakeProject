package Domain.Game;

import Domain.Board.BoardModels;

/**
 * Interface access for different gameModels.
 */
public interface GameModels {

    /**
     * Method that updates the GameState and detect eventual important gameState like GameOver.
     * Note that this method should update the GameState.
     * @param currentNanoTime Time passed since last update
     * @see BoardModels#updateBoardState(long)
     */
    void updateGameState(long currentNanoTime);

    /**
     * Helper method that retuns the Board attached to the Game.
     * @return Board for this game.
     */
    BoardModels getBoardModel();

}
