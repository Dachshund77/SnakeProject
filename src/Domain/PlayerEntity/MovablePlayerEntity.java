package Domain.PlayerEntity;

import Domain.Moveable.Movable;
import javafx.scene.input.KeyCode;

public abstract class MovablePlayerEntity extends Movable  {//TODO need javaDoc

    String name;

    public MovablePlayerEntity(double xPosition, double yPosition, double height, double width, double speed, String name) {
        super(xPosition, yPosition, height, width, speed);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void handleUserInput(KeyCode keyCode);
}
