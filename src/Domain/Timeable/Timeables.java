package Domain.Timeable;


import Domain.Sprite.Sprites;
import javafx.scene.paint.Color;

public interface Timeables extends Sprites {

    void update(long time); //Todo need javaDoc

    double getCurrentLifetime();

    double getMaxLifeTime();

    void setPaint(Color paint);
}
