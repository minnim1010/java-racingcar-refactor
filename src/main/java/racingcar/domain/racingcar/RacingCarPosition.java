package racingcar.domain.racingcar;

public class RacingCarPosition {
    private int position;

    public int getValue() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RacingCarPosition that = (RacingCarPosition) o;

        return position == that.position;
    }

    @Override
    public int hashCode() {
        return position;
    }

    @Override
    public String toString() {
        return "RacingCarPosition{" +
                "position=" + position +
                '}';
    }

    public void forward() {
        ++position;
    }
}
