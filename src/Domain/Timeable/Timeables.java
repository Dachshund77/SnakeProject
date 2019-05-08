package Domain.Timeable;


import Domain.Game.GameModels;
import Domain.Sprite.Sprites;

public interface Timeables extends Sprites {

    void update(long time, GameModels gameModels); //Todo need javaDoc

    double getCurrentLifetime();

    double getMaxLifeTime();

    void setMaxLifeTime(double newMaxLifeTime);
}
