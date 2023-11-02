package racingcar.game;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import racingcar.domain.RacerRegistry;
import racingcar.domain.racingcar.RacingCar;
import racingcar.factory.RacingCarFactory;
import racingcar.game.vo.RacerPositionVo;

public class RacingRoundProcessor {
    private final List<RacingCar> racingCars;

    public RacingRoundProcessor(RacingCarFactory racingCarFactory, RacerRegistry racerRegistry) {
        racingCars = racerRegistry.getRacingCarNames().stream()
                .map(racingCarFactory::createRacingCar)
                .collect(Collectors.toList());
    }

    public void progressRound() {
        racingCars.forEach(RacingCar::move);
    }

    public List<RacerPositionVo> getRacerPositions() {
        return racingCars.stream()
                .map(car -> new RacerPositionVo(car.getName(), car.getPosition()))
                .toList();
    }

    public List<String> getWinners() {
        Optional<Integer> maxPosition = racingCars.stream()
                .map(RacingCar::getPosition)
                .max(Integer::compareTo);

        return maxPosition.map(max -> racingCars.stream()
                        .filter(racer -> racer.getPosition() == max)
                        .map(RacingCar::getName)
                        .toList())
                .orElse(Collections.emptyList());
    }

    @Override
    public String toString() {
        return "RacingRoundProcessor{" +
                "racingCars=" + racingCars +
                '}';
    }
}
