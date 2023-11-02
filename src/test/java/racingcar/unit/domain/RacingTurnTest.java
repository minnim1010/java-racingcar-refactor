package racingcar.unit.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.common.config.RacingCarRule;
import racingcar.domain.RacingTurn;

class RacingTurnTest {

    @Nested
    @DisplayName("시도 횟수의 유효성 검사 시")
    class create {

        @DisplayName("성공하면 성공적으로 생성한다.")
        @Test
        void success() {
            //given
            int totalTurn = RacingCarRule.MAX_TOTAL_TURN;

            //when
            RacingTurn racingTurn = RacingTurn.from(totalTurn);

            //then
            assertThat(racingTurn.getCount()).isEqualTo(totalTurn);
        }

        @DisplayName("시도 횟수가 " + RacingCarRule.MIN_TOTAL_TURN + "보다 작거나 " + RacingCarRule.MAX_TOTAL_TURN
                + "보다 크다면 예외를 발생시킨다.")
        @ValueSource(ints = {RacingCarRule.MIN_TOTAL_TURN - 1, RacingCarRule.MAX_TOTAL_TURN + 1})
        @ParameterizedTest
        void fail_InvalidRange(int totalTurn) {
            //given
            //when then
            assertThatThrownBy(() -> RacingTurn.from(totalTurn))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}