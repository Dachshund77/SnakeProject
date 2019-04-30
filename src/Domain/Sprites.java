package Domain;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public interface Sprites {

    void update(double time);
    void render(GraphicsContext gc);
    Rectangle2D getBoundary();
    boolean intersects(Sprite s);
}
