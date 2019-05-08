package Domain.Food;

import Domain.Sprite.Sprites;

/**
 * Interface access method for all Food objects.
 */
public interface Foods extends Sprites {

    /**
     * Helper method that gets the Score value for this food.
     * @return double with the ScoreValue
     */
    double getScoreValue();

    /**
     * Helper method that gets the Length that will be added to the tail.
     * @return double of the length
     * @see Domain.PlayerEntity.SnakeHead
     * @see Domain.Timeable.SnakeBody
     * @see Domain.Timeable.Timeables
     */
    double getAddedLength();
}
