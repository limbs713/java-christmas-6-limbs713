package christmas.util;

import static christmas.Constant.RegEx.INPUT_MENU_REG_EX;
import static christmas.Constant.RegEx.INPUT_RESERVATION_DATE_REG_EX;

import java.util.regex.Pattern;

public class Validator {
    private static final Pattern menuPattern = INPUT_MENU_REG_EX.getRegExPattern();
    private static final Pattern reservationDatePattern = INPUT_RESERVATION_DATE_REG_EX.getRegExPattern();

    public static boolean isValidMenuInput(String input) {
       return menuPattern.matcher(input).matches();
    }

    public static boolean isValidReservationDateInput(String input) {
        return reservationDatePattern.matcher(input).matches();
    }
}
