package christmas.domain.reserve;

import christmas.Constant.MenuType;
import christmas.domain.order.MenuItem;
import java.util.Optional;

public class Dish {
    private final String name;
    private final int price;
    private final int quantity;

    public Dish(MenuItem menuItem , int quantity) {
        this.name = menuItem.getName();
        this.price = menuItem.getPrice();
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return quantity * price;
    }

    public int getQuantity() { return quantity; }

    public Optional<MenuType> getMenuType() {
        return MenuType.getType(name);
    }
}
