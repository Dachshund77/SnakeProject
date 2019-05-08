package Domain.Moveable;

import Domain.Game.GameModels;
import Domain.Sprite.Sprites;

public interface Moveables extends Sprites{

    void move(long time, GameModels gameModels);

    /**
     * Method to determine if two Sprites are overlapping.
     * @param s The other Sprite.
     * @return true if the Sprites are overlapping
     */
    boolean intersects(Sprites s);

    void handleCollision(Sprites s, GameModels gameModels);
}
