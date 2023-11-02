package racingcar.domain.racingcar;

import racingcar.domain.race.Raceable;
import racingcar.domain.race.strategy.MovementStrategy;

public class RacingCar implements Raceable {
    private final RacingCarName racingCarName;
    private final RacingCarPosition racingCarPosition;
    private final MovementStrategy movementStrategy;

    public RacingCar(String racingCarName, MovementStrategy movementStrategy) {
        this.racingCarName = new RacingCarName(racingCarName);
        this.racingCarPosition = new RacingCarPosition();
        this.movementStrategy = movementStrategy;
    }

    public static RacingCar of(String racingCarName, MovementStrategy movementStrategy) {
        return new RacingCar(racingCarName, movementStrategy);
    }

    public String getName() {
        return racingCarName.getName();
    }

    public int getPosition() {
        return racingCarPosition.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RacingCar racingCar = (RacingCar) o;

        return racingCarName.equals(racingCar.racingCarName);
    }

    @Override
    public int hashCode() {
        return racingCarName.hashCode();
    }

    @Override
    public String toString() {
        return "Racer{" +
                "name='" + racingCarName + '\'' +
                ", position=" + racingCarPosition +
                '}';
    }

    @Override
    public void move() {
        if (movementStrategy.isMoveable()) {
            racingCarPosition.forward();
        }
    }
}
