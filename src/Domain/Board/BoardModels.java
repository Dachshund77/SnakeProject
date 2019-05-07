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

    ArrayList<Moveables> getMovables(); //TODO need Javadoc

    ArrayList<Timeables> getTimeables(); //TODO need javadoc

    ArrayList<Foods> getFoods(); //TODO need javadoc

    ArrayList<TimeMoveable> getTimeMovables(); //TODO need javadoc

    ArrayList<Sprites> getAllSprites();

    ArrayList<Timeables> getAllTimeAbles();

    ArrayList<Moveables> getAllMoveables();

    boolean removeSprite(Sprites s);

    boolean removePlayerEntity(PlayerEntities p);

    boolean removeMoveable(Moveable m);

    boolean removeTimeable(Timeable t);

    boolean removeTimeMovable(TimeMoveable tm);

    boolean removeFood(Foods f);

    boolean addSprite(Sprites s);

    boolean addPlayerEntity(PlayerEntities p);

    boolean addMoveable(Moveable m);

    boolean addTimeable(Timeable t);

    boolean addTimeMoveable(TimeMoveable tm);

    boolean addFood(Foods f);

    double getHeight();

    double getWidth();

    double getRandomY();

    double getRandomX();
}
