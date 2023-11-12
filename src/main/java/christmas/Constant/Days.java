package christmas.Constant;

import java.util.Arrays;

public enum Days {
    MONDAY(4), TUESDAY(5), WEDNESDAY(6), THURSDAY(0), FRIDAY(1), SATURDAY(2), SUNDAY(3);

    private static final int DAY_PER_WEEK = 7;

    private final int date;

    Days(int date) {
        this.date = date;
    }

    private int getDate() {
        return date;
    }

    public static Days valueOf(int todayDate) {
        int normalizedDate = todayDate % DAY_PER_WEEK;
        return Arrays.stream(Days.values())
                .filter(days -> days.getDate() == normalizedDate)
                .findFirst()
                .orElseThrow();
    }


}
