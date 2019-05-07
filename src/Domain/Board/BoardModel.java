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
import java.util.Collection;


public abstract class BoardModel implements BoardModels { //TODO could have better control to prevent pollution

    double height;
    double width;

    private ArrayList<Sprites> sprites;
    private ArrayList<PlayerEntities> playerEntities;
    private ArrayList<Moveables> movables;
    private ArrayList<Timeables> timeables;
    private ArrayList<TimeMoveable> timeMovables;
    private ArrayList<Foods> foods; //TODO should be split at some point into moveable food and timeable food

    public BoardModel(double height, double width) {
        this.height = height;
        this.width = width;
        this.sprites = new ArrayList<>();
        this.playerEntities = new ArrayList<>();
        this.movables = new ArrayList<>();
        this.timeables = new ArrayList<>();
        this.timeMovables = new ArrayList<>();
        this.foods = new ArrayList<>();
    }

    @Override
    public ArrayList<Sprites> getSprites() {
        return sprites;
    }

    @Override
    public ArrayList<PlayerEntities> getMovablePlayerEntities() {
        return playerEntities;
    }

    @Override
    public ArrayList<Moveables> getMovables() {
        return movables;
    }

    @Override
    public ArrayList<Timeables> getTimeables() {
        return timeables;
    } //TODO need javadoc

    @Override
    public ArrayList<TimeMoveable> getTimeMovables() {
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
        returnArrayList.addAll((Collection<? extends Sprites>) playerEntities);
        returnArrayList.addAll((Collection<? extends Sprites>) movables);
        returnArrayList.addAll((Collection<? extends Sprites>) timeables);
        returnArrayList.addAll(timeMovables);
        returnArrayList.addAll((Collection<? extends Sprites>) foods);

        return returnArrayList;

    }

    @Override
    public ArrayList<Timeables> getAllTimeAbles() {
        ArrayList<Timeables> returnArrayList = new ArrayList<>();

        returnArrayList.addAll(timeables);
        returnArrayList.addAll(timeMovables);


        return returnArrayList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Moveables> getAllMoveables() {
        ArrayList<Moveables> returnArrayList = new ArrayList<>();

        returnArrayList.addAll(playerEntities);
        returnArrayList.addAll(movables);
        returnArrayList.addAll(timeMovables);

        return returnArrayList;
    }

    public boolean removeSprite(Sprites s) {
        return sprites.remove(s);
    }

    public boolean removePlayerEntity(PlayerEntities movablePlayerEntity) {
        return playerEntities.remove(movablePlayerEntity);
    }

    public boolean removeMoveable(Moveable m) {
        return movables.remove(m);
    }

    public boolean removeTimeable(Timeable t) {
        return timeables.remove(t);
    }

    public boolean removeTimeMovable(TimeMoveable tm) {
        return timeMovables.remove(tm);
    }

    public boolean removeFood(Foods f) {
        return foods.remove(f);
    }

    public boolean addSprite(Sprites s) {
        return sprites.add(s);
    }

    public boolean addPlayerEntity(PlayerEntities p) {
        return playerEntities.add(p);
    }

    public boolean addMoveable(Moveable m) {
        return movables.add(m);
    }

    public boolean addTimeable(Timeable t) {
        return timeables.add(t);
    }

    public boolean addTimeMoveable(TimeMoveable tm) {
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
