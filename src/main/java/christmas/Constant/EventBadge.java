package christmas.Constant;

import java.util.Arrays;
import java.util.Comparator;

public enum EventBadge {
    NO_BADGE(0, "없음"), STAR(5000, "별"), TREE(10000, "트리"), SANTA(20000, "산타");

    private final int condition;
    private final String name;

    EventBadge(int condition, String name) {
        this.condition = condition;
        this.name = name;
    }

    private int getCondition() {
        return condition;
    }

    public String getName() {
        return name;
    }

    public static EventBadge getBadge(int totalDiscountPrice) {
        return Arrays.stream(EventBadge.values())
                .filter(badge -> badge.getCondition() <= totalDiscountPrice)
                .max(Comparator.comparingInt(EventBadge::getCondition))
                .orElse(NO_BADGE);
    }
}
