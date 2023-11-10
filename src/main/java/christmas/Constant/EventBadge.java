package christmas.Constant;

import java.util.Arrays;
import java.util.Comparator;

public enum EventBadge {
    NO_BADGE(0), STAR(5000), TREE(10000), SANTA(20000);

    private final int condition;

    EventBadge(int condition) {
        this.condition = condition;
    }

    private int getCondition() {
        return condition;
    }

    public static EventBadge getBadge(int totalDiscountPrice) {
        return Arrays.stream(EventBadge.values())
               .filter(badge -> badge.getCondition() <= totalDiscountPrice)
                .max(Comparator.comparingInt(EventBadge::getCondition))
                .orElse(NO_BADGE);
    }
}
