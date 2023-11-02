package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import racingcar.validator.RacerValidator;

public class RacerRegistry {

    private final List<String> racingCarNames = new ArrayList<>();

    public void addAll(List<String> racingCarNames) {
        RacerValidator.validateRacerSize(racingCarNames);
        RacerValidator.validateDuplicatedRacerName(racingCarNames);

        this.racingCarNames.addAll(racingCarNames);
    }

    public List<String> getRacingCarNames() {
        return racingCarNames;
    }
}
