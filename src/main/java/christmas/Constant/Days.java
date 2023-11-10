package christmas.Constant;

import java.util.Arrays;

public enum Days {
    MONDAY(3), TUESDAY(4), WEDNESDAY(5), THURSDAY(6), FRIDAY(0), SATURDAY(1), SUNDAY(2);

    private static final int START_DISCOUNT_DAY = 1;
    private static final int DAY_PER_WEEK = 7;

    private final int date;

    Days(int date) {
        this.date = date;
    }

    private int getDate() {
        return date;
    }

    public static Days valueOf(int todayDate) {
        int normalizedDate = (todayDate - START_DISCOUNT_DAY) % DAY_PER_WEEK;
        return Arrays.stream(Days.values())
                .filter(days -> days.getDate() == normalizedDate)
                .findFirst()
                .orElseThrow();
    }


}
