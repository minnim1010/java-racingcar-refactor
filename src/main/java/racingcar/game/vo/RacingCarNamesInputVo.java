package racingcar.game.vo;

import java.util.Arrays;
import java.util.List;
import racingcar.common.config.RacingCarRule;
import racingcar.domain.racer.RacingCar;
import racingcar.validator.Validator;

public record RacingCarNamesInputVo(String input) {

    public RacingCarNamesInputVo {
        Validator.validateLength(input, RacingCarRule.MAX_RACER_NAME_INPUT_LENGTH);
        Validator.validateHasText(input);
    }

    public List<RacingCar> toRacingCarList() {
        return Arrays.stream(input.split(RacingCarRule.DELIMITER))
                .map(RacingCar::from)
                .toList();
    }
}
