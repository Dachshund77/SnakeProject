package Domain.Game;

import Domain.Board.BoardModels;
import Domain.Food.Food;
import Domain.Moveable.Moveables;
import Domain.Timeable.SnakeBody;
import Domain.PlayerEntity.SnakeHead;
import Domain.Sprite.Sprites;
import Domain.Timeable.Timeables;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public abstract class GameModel implements GameModels { //TODO need JavaDoc

    BoardModels boardModel;
    private boolean gameOver = false;
    ArrayList<Sprites> addQue = new ArrayList<>();

    @Override
    public void updateGameState(long milSecPassed) { //TODO clean up
        //Track needed values
        ArrayList<Moveables> moveablesArrayList = new ArrayList<>();
        ArrayList<Sprites> toBeRemoved = new ArrayList<>();

        //Getting needed values
        ArrayList<Sprites> sprites = boardModel.getSprites();

        //Starting iteration
        for (Sprites sprite : sprites) {
            if (sprite.isRemoved()) {
                toBeRemoved.add(sprite);
            } else {
                if (sprite instanceof Timeables) {
                    Timeables timeables = (Timeables) sprite;
                    timeables.update(milSecPassed, this);
                }
                if (sprite instanceof Moveables) {
                    Moveables moveables = (Moveables) sprite;
                    moveables.move(milSecPassed, this);
                    moveablesArrayList.add(moveables);
                }
            }
        }
        // Add sprites that were qued up in the main loop
        for (Sprites s : addQue) {
            boardModel.addSprite(s);
        }
        addQue.clear();

        // Garbage collect removed sprites
        for (Sprites s : toBeRemoved) {
            boardModel.removeSprite(s);
        }

        //Detecting and acting on Collision
        for (Moveables moveables : moveablesArrayList) {
            detectCollision(moveables, sprites);
        }

        //Spawn game element (foods)
        spawnNextFood();

        //Detect game end
        detectGameEnd();
    }


    @Override
    public BoardModels getBoardModel() {
        return boardModel;
    }

    private void detectCollision(Moveables moveable, ArrayList<Sprites> sprites) {
        for (Sprites sprite : sprites) {
            if (sprite.intersects(moveable) && !moveable.equals(sprite)) {
                moveable.handleCollision(sprite, this);
            }
        }
    }

    @Override
    public void detectGameEnd() {
        if (boardModel.getMovablePlayerEntities().size() == 0) {
            gameOver = true;
            System.out.println("GAME OVER");
        }
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public boolean addSpriteQue(Sprites s) {
         return addQue.add(s);
    }

}
