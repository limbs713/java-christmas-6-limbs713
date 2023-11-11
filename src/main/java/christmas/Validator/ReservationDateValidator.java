package christmas.Validator;

import static christmas.Constant.Message.INPUT_RESERVATION_DATE_ERROR_MESSAGE;

import christmas.Constant.RegEx;
import java.util.regex.Pattern;

public class ReservationDateValidator {
    private static final Pattern pattern = RegEx.INPUT_RESERVATION_DATE_REG_EX.getRegExPattern();

    public static void isValid(String input) {
        if (!pattern.matcher(input).matches()) {
            throw new IllegalArgumentException(INPUT_RESERVATION_DATE_ERROR_MESSAGE.getMessage());
        }
    }

}
