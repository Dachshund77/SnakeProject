package Domain.Game;

import Domain.Board.BoardModels;
import Domain.Moveable.Movable;
import Domain.Moveable.SnakeBody;
import Domain.PlayerEntity.MovablePlayerEntity;
import Domain.PlayerEntity.SnakeHead;
import Domain.Sprite.Sprites;
import Domain.Timeable.Timeable;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class GameModel implements GameModels { //TODO need JavaDoc

    BoardModels boardModel;

    @Override
    public void updateGameState(long milSecPassed) {

        // Place a new Snakebody
        ArrayList<MovablePlayerEntity> playerEntities = boardModel.getMovablePlayerEntities();
        for (PlayerEntities playerEntity : playerEntities) {
            if (playerEntity instanceof SnakeHead) {
                SnakeHead snakeHead = (SnakeHead) playerEntity;
                placeSnakeBody(snakeHead);
            }
        }

        //move board aka a move movables
        moveMovables(milSecPassed);

        // Update TimeAbles
        updateTimeables(milSecPassed);

        //get any collision with player enteties
        detectCollision();

        SnakeHead snakeHead = boardModel.getSnakeHead();
        ArrayList<Sprites> sprites = boardModel.getSprites();
        for (Sprites sprite : sprites) {
            if (sprite.intersects(snakeHead)) { //The order of this actual matters
                System.out.println("Snakehead intersects with " + sprite.getClass().getName());
            }
        }
    }

    @Override
    public BoardModels getBoardModel() {
        return boardModel;
    }

    private void moveMovables(long milSecPassed) {
        ArrayList<Movable> moveables = boardModel.getAllMoveables();
        for (Moveables moveable : moveables) {
            moveable.move(milSecPassed);
        }

    }

    private void updateTimeables(long milSecPassed) {
        // Update Timeables
        ArrayList<Movable> timeables = boardModel.getAllTimeAbles();
        for (Iterator<Movable> iterator = timeables.iterator(); iterator.hasNext();){
            Timeable timeable = (Timeable) iterator.next();
            timeable.update(milSecPassed);
            if (timeable.getCurrentLifetime() > timeable.getMaxLifeTime()){
                iterator.remove();
            }
        }
    }

    private void detectCollision() {
        ArrayList<Movable> moveables = boardModel.getAllMoveables();
        ArrayList<Sprites> sprites = boardModel.getAllSprites();
        for (Moveables moveable : moveables) {
            for (Sprites sprite : sprites) {
                moveable.
            }
        }
    }

    private void handeCollision(){

    }

    private void placeSnakeBody(SnakeHead snakeHead) {
        double tempxPosition = snakeHead.getxPosition();
        double tempyPosition = snakeHead.getyPosition();
        double snakeHeadHeight = snakeHead.getHeight();
        double snakeHeadWidth = snakeHead.getWidth();
        double newSnakeBodyHeight = snakeHead.getHeight(); //Could technicly be smaller then the snakehead
        double newSnakeBodyWidth = snakeHead.getWidth(); //Could technicly be smaller then the snakehead
        double snakeHeadSpeed = snakeHead.getSpeed();
        double noCollisionTime = ((newSnakeBodyWidth+snakeHeadWidth)/snakeHeadSpeed)+ ((newSnakeBodyHeight+snakeHeadHeight)/snakeHeadSpeed);
        boardModel.addTimeable(new SnakeBody(tempxPosition, tempyPosition, newSnakeBodyHeight, newSnakeBodyWidth, Color.RED, 1000,noCollisionTime));
    }


}
