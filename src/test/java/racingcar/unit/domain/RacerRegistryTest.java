package racingcar.unit.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import racingcar.common.config.RacingCarRule;
import racingcar.domain.RacerRegistry;

class RacerRegistryTest {

    private static List<String> getRacingCarNames(String... name) {
        return Arrays.asList(name);
    }

    private static List<String> getRacingCarNames(int size) {
        List<String> racingCarNames = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            racingCarNames.add(String.valueOf(i));
        }
        return racingCarNames;
    }

    @Nested
    @DisplayName("경주 자동차 여러 개를 한 번에 등록할 시")
    class addAll {

        static Stream<List<String>> successArgument() {
            return Stream.of(
                    getRacingCarNames(RacingCarRule.MIN_RACER_SIZE),
                    getRacingCarNames(RacingCarRule.MIN_RACER_SIZE + 1)
            );
        }

        static Stream<List<String>> fail_DulicatedNameArgument() {
            return Stream.of(
                    getRacingCarNames("A", "B", "A", "D"),
                    getRacingCarNames("1", "2", "3", "1")
            );
        }

        @DisplayName("성공적으로 등록한다.")
        @MethodSource("successArgument")
        @ParameterizedTest
        void success(List<String> racingCarList) {
            //given
            //when
            RacerRegistry racerRegistry = new RacerRegistry();
            racerRegistry.addAll(racingCarList);
            //then
            assertThat(racerRegistry.getRacingCarNames()).hasSize(racingCarList.size());
        }

        @DisplayName("중복 이름이 존재하면 예외를 발생시킨다.")
        @MethodSource("fail_DulicatedNameArgument")
        @ParameterizedTest
        void fail_DulicatedName(List<String> racingCarList) {
            //given
            //when then
            RacerRegistry racerRegistry = new RacerRegistry();
            assertThatThrownBy(() -> racerRegistry.addAll(racingCarList))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("등록할 수 있는 최대 개수보다 많다면 예외를 발생시킨다.")
        @Test
        void fail_GreaterThanMaxRacingCarSize() {
            //given
            List<String> racingCarList = getRacingCarNames(RacingCarRule.MAX_RACER_SIZE + 1);
            //when then
            RacerRegistry racerRegistry = new RacerRegistry();
            assertThatThrownBy(() -> racerRegistry.addAll(racingCarList))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("등록해야 하는 최소 개수보다 적다면 예외를 발생시킨다.")
        @Test
        void fail_LessThanMinRacingCarSize() {
            //given
            List<String> racingCarList = getRacingCarNames(RacingCarRule.MIN_RACER_SIZE - 1);
            //when then
            RacerRegistry racerRegistry = new RacerRegistry();
            assertThatThrownBy(() -> racerRegistry.addAll(racingCarList))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}