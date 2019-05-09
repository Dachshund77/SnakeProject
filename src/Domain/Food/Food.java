package Domain.Food;

import Domain.Sound.SoundPlayer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.Objects;

/**
 * Actual implementation of a simple food object.
 */
public class Food extends SimpleFoods {  //TODO the circle could become a image or something

    private Color paint;
    private double scoreValue;
    private double addedLength;

    public Food(double xPosition, double yPosition, double height, double width, Color paint, double scoreValue, double addedLength) {
        super(xPosition, yPosition, height, width);
        this.paint = paint;
        this.scoreValue = scoreValue;
        this.addedLength = addedLength;
    }

    /**
     * {@inheritDoc}
     * <br><br>
     * In this case the render will draw a rectangle
     */
    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(paint);
        gc.fillRect(xPosition,yPosition,width,height);
    }

    @Override
    public double getScoreValue() {
        return scoreValue;
    }

    @Override
    public double getAddedLength() {
        return addedLength;
    }

    /**
     * {@inheritDoc}
     * <br><br>
     * This implementation will also play a soundEffect of eating sounds.
     */
    @Override
    public void setIsRemoved(boolean newStatus) {
        if (!isRemoved){ //Insurance so this will only be executed once
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource("SoundEffects/Eating.wav")).getFile());
            SoundPlayer.getInstance().playSoundEffect(file);
        }
        super.setIsRemoved(newStatus);
    }
}
