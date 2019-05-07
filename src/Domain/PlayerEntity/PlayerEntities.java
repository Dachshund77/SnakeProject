package Domain.PlayerEntity;

import Domain.Moveable.Moveables;
import Domain.Sprite.Sprites;
import javafx.scene.input.KeyCode;

public interface PlayerEntities extends Moveables {
    void handleUserInput(KeyCode keyCode);
}
