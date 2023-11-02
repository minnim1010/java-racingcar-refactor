package racingcar.game.vo;

import racingcar.common.config.RacingCarRule;
import racingcar.domain.Round;
import racingcar.validator.Validator;

public record TotalRoundInput(String input) {

    public TotalRoundInput {
        Validator.validateLength(input, RacingCarRule.TOTAL_TURN_INPUT_LENGTH);
        Validator.validateHasText(input);
        Validator.validateNumeric(input);
    }

    public Round toRacingTurn() {
        return Round.from(Integer.parseInt(input));
    }
}
