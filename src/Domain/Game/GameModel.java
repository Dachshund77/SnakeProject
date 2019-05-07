package Domain.Game;

import Domain.Board.BoardModels;
import Domain.Moveable.Moveables;
import Domain.PlayerEntity.PlayerEntities;
import Domain.TimeMovable.TimeMoveable;
import Domain.Timeable.SnakeBody;
import Domain.PlayerEntity.SnakeHead;
import Domain.Sprite.Sprites;
import Domain.Timeable.Timeable;
import Domain.Timeable.Timeables;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class GameModel implements GameModels { //TODO need JavaDoc

    BoardModels boardModel;

    @Override
    public void updateGameState(long milSecPassed) {

        // Place a new Snakebody
        ArrayList<PlayerEntities> playerEntities = boardModel.getMovablePlayerEntities();
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

        //get any collision for moveables
        detectCollision();

    }

    @Override
    public BoardModels getBoardModel() {
        return boardModel;
    }

    private void moveMovables(long milSecPassed) {
        ArrayList<Moveables> moveables = boardModel.getAllMoveables();
        for (Moveables moveable : moveables) {
            moveable.move(milSecPassed);
        }

    }

    private void updateTimeables(long milSecPassed) {
        // Update Timeables
        ArrayList<Timeables> timeables = boardModel.getTimeables();
        for (Iterator<Timeables> iterator = timeables.iterator(); iterator.hasNext();){
            Timeable timeable = (Timeable) iterator.next();
            timeable.update(milSecPassed);
            if (timeable.getCollisionIgnoranceTime() < timeable.getCurrentLifetime()){
                timeable.setPaint(Color.BLUE);
            }
            if (timeable.getCurrentLifetime() > timeable.getMaxLifeTime()){
                iterator.remove();
            }
        }

        // Update TimeMovables
        ArrayList<TimeMoveable> timeMovables = boardModel.getTimeMovables();
        for (Iterator<TimeMoveable> iterator = timeMovables.iterator(); iterator.hasNext();){
            Timeables tempTimeables = iterator.next();
            tempTimeables.update(milSecPassed);
            if (tempTimeables.getCurrentLifetime() > tempTimeables.getMaxLifeTime()){
                iterator.remove();
            }
        }
    }

    private void detectCollision() {
        ArrayList<Moveables> moveables = boardModel.getAllMoveables();
        ArrayList<Sprites> sprites = boardModel.getAllSprites();
        for (Moveables moveable : moveables) {
            for (Sprites sprite : sprites) {
                if(sprite.intersects(moveable) && !moveable.equals(sprite)) {
                    moveable.handleCollision(sprite,this);
                }
            }
        }
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
