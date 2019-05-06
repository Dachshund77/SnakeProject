package Domain.PlayerEntity;

import Domain.Moveable.Moveable;

public abstract class MoveablePlayerEntity extends Moveable implements PlayerEntities  {//TODO need javaDoc

    String name;

    public MoveablePlayerEntity(double xPosition, double yPosition, double height, double width, double speed, String name) {
        super(xPosition, yPosition, height, width, speed);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
