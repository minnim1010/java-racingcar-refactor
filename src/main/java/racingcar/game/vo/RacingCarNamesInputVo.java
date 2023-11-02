package racingcar.game.vo;

import java.util.Arrays;
import java.util.List;
import racingcar.common.config.RacingCarRule;
import racingcar.validator.Validator;

public record RacingCarNamesInputVo(String input) {

    public RacingCarNamesInputVo {
        Validator.validateLength(input, RacingCarRule.MAX_RACER_NAME_INPUT_LENGTH);
        Validator.validateHasText(input);
    }

    public List<String> toList() {
        return Arrays.asList(input.split(RacingCarRule.DELIMITER));
    }
}
