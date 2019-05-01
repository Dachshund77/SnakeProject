package Domain.Game;

import Domain.Board.BoardModels;

public interface GameModels {

    void updateGameState(long currentNanoTime);

    BoardModels getBoardModel();

}
