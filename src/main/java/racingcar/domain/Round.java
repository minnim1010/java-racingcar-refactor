package racingcar.domain;

import racingcar.validator.RacerValidator;

public class Round {
    private final int count;

    protected Round(int count) {
        RacerValidator.validateIsWithinRacingTurnRange(count);

        this.count = count;
    }

    public static Round from(int totalTurn) {
        return new Round(totalTurn);
    }

    public int getCount() {
        return count;
    }
}
