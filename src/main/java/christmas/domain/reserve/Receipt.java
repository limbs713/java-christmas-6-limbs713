package christmas.domain.reserve;

import christmas.Constant.MenuType;
import christmas.domain.order.MenuItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Receipt {
    private final List<Dish> dishes = new ArrayList<>();

    private Receipt(List<MenuItem> menuItems) {
        menuItems.forEach(menuItem -> dishes.add(new Dish(menuItem)));
    }

    public static Receipt create(List<MenuItem> menuItems) {
        return new Receipt(menuItems);
    }

    public int getTotalPrice() {
        return dishes.stream()
                .mapToInt(Dish::getPrice)
                .sum();
    }

    public int getDishesByMenuType(MenuType menuType) {
        return (int) dishes.stream()
                .filter(dish -> dish.getMenuType().equals(menuType))
                .count();
    }

    public Map<String, Long> getOrderReceipt() {
        return dishes.stream()
                .collect(Collectors.groupingBy(Dish::getName, Collectors.counting()));
    }
}
