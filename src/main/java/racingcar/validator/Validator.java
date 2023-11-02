package racingcar.validator;

import java.util.regex.Pattern;
import racingcar.common.exception.ErrorMessage;

public final class Validator {

    private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");

    private Validator() {
    }

    public static void validateHasText(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.BLANK_INPUT.getMessage());
        }
    }

    public static void validateLength(String input, int maxLength) {
        if (input == null || input.length() > maxLength) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LENGTH_INPUT.getMessage());
        }
    }

    public static void validateNumeric(String input) {
        if (!NUMERIC_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMERIC_INPUT.getMessage());
        }
    }
}
