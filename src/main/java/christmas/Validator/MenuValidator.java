package christmas.Validator;

import static christmas.Constant.Message.INPUT_ORDER_MENU_ERROR_MESSAGE;

import christmas.Constant.RegEx;
import java.util.regex.Pattern;

public class MenuValidator {
    private static final Pattern pattern = RegEx.INPUT_MENU_REG_EX.getRegExPattern();

    public static void isValid(String input) {
        if (!pattern.matcher(input).matches()) {
            throw new IllegalArgumentException(INPUT_ORDER_MENU_ERROR_MESSAGE.getMessage());
        }
    }

}
