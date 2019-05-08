package Domain.Board;

import Domain.Food.Foods;
import Domain.Moveable.Moveable;
import Domain.Moveable.Moveables;
import Domain.PlayerEntity.MoveablePlayerEntity;
import Domain.PlayerEntity.PlayerEntities;
import Domain.Sprite.Sprites;
import Domain.Sprite.Wall;
import Domain.TimeMovable.TimeMoveable;
import Domain.Timeable.Timeable;
import Domain.Timeable.Timeables;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;


public abstract class BoardModel implements BoardModels { //TODO could have better control to prevent pollution

    private double height;
    private double width;
    private double wallThickness = 1;

    private ArrayList<Sprites> sprites; //RAISE A DELETE
    private ArrayList<PlayerEntities> playerEntities;
    private ArrayList<Foods> foods; //TODO should be split at some point into moveable food and timeable food

    public BoardModel(double height, double width) {
        this.height = height;
        this.width = width;
        this.sprites = new ArrayList<>();
        this.playerEntities = new ArrayList<>();
        this.foods = new ArrayList<>();

        //Making boundary walls
        Wall topWall = new Wall(0,0,wallThickness,width, Color.BLACK);
        Wall bottomWall = new Wall(0,height-wallThickness,wallThickness,width,Color.BLACK);
        Wall leftWall = new Wall(0,0,height,wallThickness,Color.BLACK);
        Wall rightWall = new Wall(width-wallThickness,0,height,wallThickness,Color.BLACK);

        addSprite(topWall);
        addSprite(bottomWall);
        addSprite(leftWall);
        addSprite(rightWall);
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
    public ArrayList<Foods> getFoods() {
        return foods;
    }

    @Override
    public boolean removeSprite(Sprites s) {
        if (s instanceof PlayerEntities){
            playerEntities.remove(s);
        }
        if (s instanceof Foods){
            foods.remove(s);
        }
        return sprites.remove(s);
    }

    @Override
    public boolean addSprite(Sprites s) {
        if (s instanceof PlayerEntities){
            playerEntities.add((PlayerEntities)s);
        }
        if (s instanceof Foods){
            foods.add((Foods)s);
        }
        return sprites.add(s);
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getRandomY(){
        Random r = new Random();
        return  (height - (wallThickness+10)) * r.nextDouble();
    }

    @Override
    public double getRandomX(){
        Random r = new Random();
        return  (width - (wallThickness+10)) * r.nextDouble();
    }
}
