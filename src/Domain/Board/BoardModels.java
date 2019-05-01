package Domain.Board;

import Domain.Sprite.Sprites;

import java.util.ArrayList;

public interface BoardModels {

    void updateBoardState(long currentNanoTime);

    ArrayList<Sprites> getAllSprites();
}
