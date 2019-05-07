package Domain.PlayerEntity;

import javafx.scene.input.KeyCode;

public class SnakeControl { //TODO javadoc

    private KeyCode up;
    private KeyCode left;
    private KeyCode down;
    private KeyCode right;

    public SnakeControl(KeyCode up, KeyCode left, KeyCode down, KeyCode right) {
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
