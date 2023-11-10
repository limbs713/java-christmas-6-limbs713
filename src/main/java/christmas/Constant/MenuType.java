package christmas.Constant;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum MenuType {
    APPETIZER(Arrays.asList("양송이수프", "타파스", "시저샐러드")),
    MAIN(Arrays.asList("티본스테이크", "바베큐립", "해산물파스타", "크리스마스파스타")),
    DESERT(Arrays.asList("초코케이크", "아이스크림")),
    BEVERAGES(Arrays.asList("제로콜라", "레드와인", "샴페인"));

    private final List<String> menuList;

    MenuType(List<String> menuList) {
        this.menuList = menuList;
    }

    public static Optional<MenuType> getType(String name) {
        for (MenuType menuType : MenuType.values()) {
            if (menuType.menuList.contains(name)) {
                return Optional.of(menuType);
            }
        }
        return Optional.empty();
    }
}
