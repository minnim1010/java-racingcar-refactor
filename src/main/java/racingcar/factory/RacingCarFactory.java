package racingcar.factory;

import racingcar.domain.race.strategy.MovementStrategy;
import racingcar.domain.racingcar.RacingCar;

public class RacingCarFactory {
    private final MovementStrategy movementStrategy;

    public RacingCarFactory(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public RacingCar createRacingCar(String name) {
        return new RacingCar(name, movementStrategy);
    }
}
