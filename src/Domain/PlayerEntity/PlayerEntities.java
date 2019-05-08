package Domain.PlayerEntity;

import Domain.Moveable.Moveables;
import Domain.Sprite.Sprites;
import javafx.scene.input.KeyCode;

/**
 * Interface Access for player entities. Most notably, those enteties will be controlled from the controller.
 */
public interface PlayerEntities extends Moveables {

    /**
     * Method that defines how a entety reacts on the input.
     * @param keyCode The key pressed by the user
     */
    void handleUserInput(KeyCode keyCode);
}
