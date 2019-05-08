package Domain.Board;

import Domain.Food.Foods;
import Domain.Moveable.Moveable;
import Domain.Moveable.Moveables;
import Domain.PlayerEntity.MoveablePlayerEntity;
import Domain.PlayerEntity.PlayerEntities;
import Domain.Sprite.Sprites;
import Domain.TimeMovable.TimeMoveable;
import Domain.Timeable.Timeable;
import Domain.Timeable.Timeables;

import java.util.ArrayList;

/**
 * Interfaces access point for the different BoardModels
 */
public interface BoardModels {

    /**
     * Method that returns all Sprites on a board that can be drawn. //TODO WRONG!
     *
     * @return All Drawable Sprites on this Board.
     */
    ArrayList<Sprites> getSprites();

    ArrayList<PlayerEntities> getMovablePlayerEntities();//TODO need javaDoc

    ArrayList<Foods> getFoods(); //TODO need javadoc

    boolean removeSprite(Sprites s);

    boolean addSprite(Sprites s);

    double getHeight();

    double getWidth();

    double getRandomY();

    double getRandomX();
}
