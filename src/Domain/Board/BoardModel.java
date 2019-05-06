package Domain.Board;

import Domain.Food.Food;
import Domain.Food.Foods;
import Domain.Moveable.Movable;
import Domain.PlayerEntity.MovablePlayerEntity;
import Domain.Sprite.Sprites;
import Domain.TimeMovable.TimeMovable;
import Domain.Timeable.Timeable;

import java.util.ArrayList;
import java.util.Collection;


public abstract class BoardModel implements BoardModels { //TODO could have better control to prevent pollution

    double height;
    double width;

    private ArrayList<Sprites> sprites;
    private ArrayList<MovablePlayerEntity> movablePlayerEntities;
    private ArrayList<Movable> movables;
    private ArrayList<Timeable> timeables;
    private ArrayList<TimeMovable> timeMovables;
    private ArrayList<Foods> foods; //TODO should be split at some point into moveable food and timeable food

    @Override
    public ArrayList<Sprites> getSprites() {
        return sprites;
    }

    @Override
    public ArrayList<MovablePlayerEntity> getMovablePlayerEntities() {
        return movablePlayerEntities;
    }

    @Override
    public ArrayList<Movable> getMovables() {
        return movables;
    }

    @Override
    public ArrayList<Timeable> getTimeables() {
        return timeables;
    } //TODO need javadoc

    @Override
    public ArrayList<TimeMovable> getTimeMovables() {
        return timeMovables;
    }

    @Override
    public ArrayList<Foods> getFoods() {
        return foods;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Sprites> getAllSprites() {

        ArrayList<Sprites> returnArrayList = new ArrayList<>();

        returnArrayList.addAll(sprites);
        returnArrayList.addAll(movablePlayerEntities);
        returnArrayList.addAll(movables);
        returnArrayList.addAll(timeables);
        returnArrayList.addAll(timeMovables);
        returnArrayList.addAll((Collection<? extends Sprites>) foods);

        return returnArrayList;

    }

    @Override
    public ArrayList<Timeable> getAllTimeAbles() {
        ArrayList<Timeable> returnArrayList = new ArrayList<>();

        returnArrayList.addAll(timeables);
        returnArrayList.addAll(timeMovables);


        return returnArrayList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Movable> getAllMoveables() {
        ArrayList<Movable> returnArrayList = new ArrayList<>();

        returnArrayList.addAll(movablePlayerEntities);
        returnArrayList.addAll(movables);
        returnArrayList.addAll(timeMovables);

        return returnArrayList;
    }

    public boolean removeSprite(Sprites s) {
        return sprites.remove(s);
    }

    public boolean removePlayerEntity(MovablePlayerEntity movablePlayerEntity) {
        return movablePlayerEntities.remove(movablePlayerEntity);
    }

    public boolean removeMoveable(Movable m) {
        return movables.remove(m);
    }

    public boolean removeTimeable(Timeable t) {
        return timeables.remove(t);
    }

    public boolean removeTimeMovable(TimeMovable tm) {
        return timeMovables.remove(tm);
    }

    public boolean removeFood(Foods f) {
        return foods.remove(f);
    }

    public boolean addSprite(Sprites s) {
        return sprites.add(s);
    }

    public boolean addPlayerEntity(MovablePlayerEntity p) {
        return movablePlayerEntities.add(p);
    }

    public boolean addMoveable(Movable m) {
        return movables.add(m);
    }

    public boolean addTimeable(Timeable t) {
        return timeables.add(t);
    }

    public boolean addTimeMoveable(TimeMovable tm) {
        return timeMovables.add(tm);
    }

    public boolean addFood(Foods f) {
        return foods.add(f);
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
}
