package christmas.Constant;

import static christmas.Constant.Days.*;

import java.util.Arrays;
import java.util.List;

public enum DayType {
    WEEKDAY(List.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY)), WEEKEND(List.of(FRIDAY, SUNDAY));

    private final List<Days> days;

    DayType(List<Days> days) {
        this.days = days;
    }

    private List<Days> getDays() {
        return days;
    }

    public static DayType valueOf(int todayDate) {
        return Arrays.stream(DayType.values())
                .filter(dayType -> dayType.getDays().contains(Days.valueOf(todayDate)))
                .findFirst()
                .orElseThrow();
    }
}
