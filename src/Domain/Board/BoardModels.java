package Domain.Board;

import Domain.Food.Food;
import Domain.Food.Foods;
import Domain.Moveable.Movable;
import Domain.PlayerEntity.MovablePlayerEntity;
import Domain.Sprite.Sprites;
import Domain.TimeMovable.TimeMovable;
import Domain.Timeable.Timeable;

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

    ArrayList<MovablePlayerEntity> getMovablePlayerEntities();//TODO need javaDoc

    ArrayList<Movable> getMovables(); //TODO need Javadoc

    ArrayList<Timeable> getTimeables(); //TODO need javadoc

    ArrayList<Foods> getFoods(); //TODO need javadoc

    ArrayList<TimeMovable> getTimeMovables(); //TODO need javadoc

    ArrayList<Sprites> getAllSprites();

    ArrayList<Timeable> getAllTimeAbles();

    ArrayList<Movable> getAllMoveables();

    boolean removeSprite(Sprites s);

    boolean removePlayerEntity(MovablePlayerEntity movablePlayerEntity);

    boolean removeMoveable(Movable m);

    boolean removeTimeable(Timeable t);

    boolean removeTimeMovable(TimeMovable tm);

    boolean removeFood(Foods f);

    boolean addSprite(Sprites s);

    boolean addPlayerEntity(MovablePlayerEntity p);

    boolean addMoveable(Movable m);

    boolean addTimeable(Timeable t);

    boolean addTimeMoveable(TimeMovable tm);

    boolean addFood(Foods f);
}
