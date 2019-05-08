package Domain.PlayerEntity;

import javafx.scene.input.KeyCode;

/**
 * SnakeHead component that makes changing easier.
 */
public class SnakeControl { //TODO there need to be made some consideration if this is replaceable with a simpe map

    private KeyCode up;
    private KeyCode left;
    private KeyCode down;
    private KeyCode right;

    public SnakeControl(KeyCode up, KeyCode left, KeyCode down, KeyCode right) { //TODO Theres is a strong case to be made to just hardcode those into the snakehead
        this.up = up;
        this.left = left;
        this.down = down;
        this.right = right;
    }

    public KeyCode getUp() {
        return up;
    }

    public KeyCode getLeft() {
        return left;
    }

    public KeyCode getDown() {
        return down;
    }

    public KeyCode getRight() {
        return right;
    }
}
