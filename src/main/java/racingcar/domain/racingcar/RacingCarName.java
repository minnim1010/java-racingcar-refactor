package racingcar.domain.racingcar;

import java.util.Objects;
import racingcar.validator.RacerValidator;

public class RacingCarName {
    private final String name;

    public RacingCarName(String name) {
        RacerValidator.validateRacerNameLength(name);
        RacerValidator.validateRacerNameFormat(name);

        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RacingCarName that = (RacingCarName) o;

        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
