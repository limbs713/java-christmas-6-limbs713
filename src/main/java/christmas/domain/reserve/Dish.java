package christmas.domain.reserve;

import christmas.Constant.MenuType;
import christmas.domain.order.MenuItem;
import java.util.Optional;

public class Dish {
    private final String name;
    private final int price;

    public Dish(MenuItem menuItem) {
        this.name = menuItem.getName();
        this.price = menuItem.getPrice();
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Optional<MenuType> getMenuType() {
        return MenuType.getType(name);
    }
}
