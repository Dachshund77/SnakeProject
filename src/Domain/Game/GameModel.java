package Domain.Game;

import Domain.Board.BoardModels;
import Domain.Moveable.Moveables;
import Domain.Sound.SoundPlayer;
import Domain.Sprite.Sprites;
import Domain.Timeable.Timeables;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Abstract class that contains the common implementations for GameModels.
 *
 * @see GameModels
 */
public abstract class GameModel implements GameModels {

    /**
     * Field to store the BoardModels. The BoardModels are container to store all Sprites.
     *
     * @see BoardModels
     */
    BoardModels boardModel;

    /**
     * Flag that signals if the game is over. Set by {@link #detectGameEnd()}.
     */
    private boolean gameOver = false;

    /**
     * ArrayList used to avoid ConcurrentModificationExceptions. The que will be emptied with each call of {@link #updateGameState(long)}
     * and added to the Board.
     * Any sprites that should be added to the Board should be done via {@link #addSpriteQue(Sprites)}.
     */
    private ArrayList<Sprites> addQue = new ArrayList<>();

    private long lastNanoTime = System.nanoTime();
    private Canvas gameCanvas;

    public GameModel(BoardModels boardModel, Canvas gameCanvas) {
        this.boardModel = boardModel;
        this.gameCanvas = gameCanvas;
    }

    @Override
    public void startGameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                // ask if the game has ended
                if (isGameOver()) {
                    stop();
                }

                // set needed value to keep track of time passed
                long timePassedInMilliseconds = ((currentNanoTime - lastNanoTime) / 1000000);
                lastNanoTime = currentNanoTime;

                //Update game, here the game logic will advance
                updateGameState(timePassedInMilliseconds);

                //Clear the canvas of everything
                GraphicsContext gameCanvasGC = gameCanvas.getGraphicsContext2D();
                gameCanvasGC.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight()); //Whole canvas

                // Draw sprites
                ArrayList<Sprites> sprites = getBoardModel().getSprites();
                for (Sprites sprite : sprites) {
                    sprite.render(gameCanvasGC);
                }
            }
        }.start();
    }

    /**
     * {@inheritDoc}
     * <br><br>
     * In order this method will:
     * <ul>
     * <li>{@link Timeables#update(long, GameModels) Update} all {@link Timeables}</li>
     * <li>{@link Moveables#move(long, GameModels) Move} all {@link Moveables}</li>
     * <li>Add the Sprites in the {@link #addQue}</li>
     * <li>Remove {@link Sprites} with the {@link Sprites#isRemoved() isRemoved} flag raised.</li>
     * <li>Detect Collision via {@link #detectCollision(Moveables, ArrayList)}</li>
     * <li>Spawn Food via {@link #spawnNextFood()}</li>
     * <li>Detect game end via {@link #detectGameEnd()}</li>
     * </ul>
     *
     * @param milSecPassed Milliseconds passed since last call.
     */
    @Override
    public void updateGameState(long milSecPassed) {
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

    /**
     * Helper method that handel's the detection of collision, given a {@link Moveables}.
     * The detection is done with the help of {@link Sprites#intersects(Sprites)}.
     * This method will call the Movables {@link Moveables#handleCollision(Sprites, GameModels) handleCollision} method,
     * if a Collision is detected.
     *
     * @param moveable The movables we want to detect a Collisions for.
     * @param sprites  A list of Sprites we want to check against our Movable.
     */
    private void detectCollision(Moveables moveable, ArrayList<Sprites> sprites) {
        for (Sprites sprite : sprites) {
            if (sprite.intersects(moveable) && !moveable.equals(sprite)) {
                moveable.handleCollision(sprite, this);
            }
        }
    }

    /**
     * {@inheritDoc}
     * <br><br>
     * This implementation will raise the {@link #gameOver} flag only if the are no Player Entities left on the Board.
     * This implementation also disposed off the background music and start the game over sound effect.
     *
     * @see BoardModels
     */
    @Override
    public void detectGameEnd() {
        if (boardModel.getMovablePlayerEntities().size() == 0) {
            if (!gameOver) { //Makes that this will only be executed once

                //Dispose of background music
                SoundPlayer.getInstance().disposeBackgroundMusic();

                //Play game over sound effect
                ClassLoader classLoader = getClass().getClassLoader();
                File file = new File(Objects.requireNonNull(classLoader.getResource("SoundEffects/GameOver.wav")).getFile());
                SoundPlayer.getInstance().playSoundEffect(file);
            }
            gameOver = true;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return The value of {@link #gameOver} field.
     */
    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * {@inheritDoc}
     *
     * @see #addQue
     */
    @Override
    public boolean addSpriteQue(Sprites s) {
        return addQue.add(s);
    }


}
