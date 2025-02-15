package christmas.constant;

import java.util.regex.Pattern;

public enum RegEx {
    INPUT_RESERVATION_DATE_REG_EX("^([1-9]|[1-2][0-9]|3[0-1])$"),
    INPUT_MENU_REG_EX("^([가-힣]+-([1-9]|1[0-9]|20),)*[가-힣]+-([1-9]|1[0-9]|20)$"),
    PARSING_MENU_REG_EX("([가-힣]+)-([1-9])+");

    private final Pattern regEx;

    RegEx(String regEx) {
        this.regEx = Pattern.compile(regEx);
    }

    public Pattern getRegExPattern() {
        return regEx;
    }
}
