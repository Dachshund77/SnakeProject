package Domain.Timeable;


import Domain.Game.GameModels;
import Domain.Sprite.Sprites;

/**
 * Interface Acces method for all Timables.
 * A Timetable is a Sprite that reacts or changes overtime.
 */
public interface Timeables extends Sprites {

     /**
     * Method that updates the Sprite on the {@link Domain.Board.BoardModels BoardModel}. This method will be called
     * repeatedly from the {@link GameModels#updateGameState(long) mainGameLoop}.
     * @param milSecPassed Milliseconds passed since last move.
     * @param gameModels The gameModel calling this method.
     */
    void update(long milSecPassed, GameModels gameModels);

    /**
     * Helper method that get the CurrentLifetime of a Timeables.
     * The lifetime of a Timeables start at 0 and is incremented by the {@link #update(long, GameModels)} method.
     * @return The current lifetime.
     * @see GameModels
     */
    double getCurrentLifetime();

    /**
     * Helper method that gets the MaxLifetime of a Timeables.
     * @return MaxLifeTime of a Timeables.
     */
    double getMaxLifeTime();

    /**
     * helper method that sets the MaxLifetime of a Timeables.
     * @param newMaxLifeTime The value this field will set to.
     */
    void setMaxLifeTime(double newMaxLifeTime);
}
