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
import java.util.Iterator;

public abstract class GameModel implements GameModels { //TODO need JavaDoc

    BoardModels boardModel;
    private boolean gameOver = false;

    @Override
    public void updateGameState(long milSecPassed) { //TODO clean up
        System.out.println("GameModel.updateGameState");
/*
        // Place a new Snakebody
        ArrayList<PlayerEntities> playerEntities = boardModel.getMovablePlayerEntities();
        for (PlayerEntities playerEntity : playerEntities) {
            if (playerEntity instanceof SnakeHead) {
                SnakeHead snakeHead = (SnakeHead) playerEntity;
                placeSnakeBody(snakeHead);
            }
        }
*/
        //Track needed values
        ArrayList<Moveables> moveablesArrayList = new ArrayList<>();
        ArrayList<Sprites> toBeRemoved = new ArrayList<>();
        //Getting needed values
        ArrayList<Sprites> sprites = boardModel.getSprites();
        //We have to use an iterator to avoid ConcurrentModificationException
        for (Sprites sprite : sprites) {
            //Testing if the Sprite has been removed
            if (sprite.isRemoved()) {
                toBeRemoved.add(sprite);
            } else { //Executing update for that sprites
                if (sprite instanceof Timeables) {
                    System.out.println("Instance of Timables");
                    Timeables timeables = (Timeables) sprite;
                    timeables.update(milSecPassed);
                }
                if (sprite instanceof Moveables) {
                    System.out.println("Instance of moveables");
                    Moveables moveables = (Moveables) sprite;
                    moveables.move(milSecPassed);
                    moveablesArrayList.add(moveables);
                }
            }
        }
        // Garbage collect removed sprites
        for (Sprites s : toBeRemoved) {
            boardModel.removeSprite(s);
        }

        //Detecting and acting on Collision
        for (Moveables moveables : moveablesArrayList) {
            detectCollision(moveables, sprites);
        }

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


    private void placeSnakeBody(SnakeHead snakeHead) {
        // Only place if in movement
        if (snakeHead.getxVelocity() != 0 || snakeHead.getyVelocity() != 0) {
            double tempxPosition = snakeHead.getxPosition();
            double tempyPosition = snakeHead.getyPosition();
            double snakeHeadHeight = snakeHead.getHeight();
            double snakeHeadWidth = snakeHead.getWidth();
            double newSnakeBodyHeight = snakeHead.getHeight(); //Could technicly be smaller then the snakehead
            double newSnakeBodyWidth = snakeHead.getWidth(); //Could technicly be smaller then the snakehead
            double snakeHeadSpeed = snakeHead.getSpeed();
            double bodyLength = snakeHead.getBodyLength();
            double noCollisionTime = ((newSnakeBodyWidth + snakeHeadWidth) / snakeHeadSpeed) + ((newSnakeBodyHeight + snakeHeadHeight) / snakeHeadSpeed);
            boardModel.addSprite(new SnakeBody(tempxPosition, tempyPosition, newSnakeBodyHeight, newSnakeBodyWidth, Color.RED, bodyLength, noCollisionTime));
        }
    }

    @Override
    public void spawnNextFood() {
        double x = boardModel.getRandomX();
        double y = boardModel.getRandomY();

        Food food = new Food(x, y, 10, 10, Color.GREEN, 100, 500);
        boardModel.addSprite(food);
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
}
