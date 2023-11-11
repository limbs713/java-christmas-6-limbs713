package christmas.domain.reserve;

import christmas.Constant.MenuType;
import christmas.domain.order.MenuItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Receipt {
    private final List<Dish> dishes = new ArrayList<>();

    private Receipt(Map<MenuItem, Integer> menuItems) {
        menuItems.forEach((menuItem, count) -> dishes.add(new Dish(menuItem, count)));
    }

    public static Receipt create(Map<MenuItem, Integer> menuItems) {
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

    public Map<String, Integer> getOrderReceipt() {
        return dishes.stream()
                .collect(Collectors.toMap(Dish::getName, Dish::getQuantity));
    }
}
