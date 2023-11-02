package racingcar.domain.race.strategy;

import racingcar.util.Random;

public class RandomNumberBasedMovementStrategy implements MovementStrategy {
    private static final int MIN = 0;
    private static final int MAX = 9;
    private static final int MOVING_FORWARD_THRESHOLD = 4;

    private final Random random;

    public RandomNumberBasedMovementStrategy(Random random) {
        this.random = random;
    }

    public boolean isMoveable() {
        int randomNumber = random.getRandomNumberInRange(MIN, MAX);
        return randomNumber >= MOVING_FORWARD_THRESHOLD;
    }
}
