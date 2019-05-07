package Domain.Game;

import Domain.Board.BoardModels;

/**
 * Interface access for different gameModels.
 */
public interface GameModels {

    /**
     * Method that updates the GameState and detect eventual important gameState like GameOver.
     * Note that this method should move the GameState.
     * @param milSecPassed Time passed since last move
     */
    void updateGameState(long milSecPassed);

    /**
     * Helper method that retuns the Board attached to the Game.
     * @return Board for this game.
     */
    BoardModels getBoardModel();

    boolean hasEnded();

    void handleGameEnd();
}
