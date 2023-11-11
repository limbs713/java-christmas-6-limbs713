package christmas.repository;

import christmas.domain.order.MenuBoard;
import christmas.domain.order.MenuItem;

public class OrderRepository {
    private final MenuBoard menuBoard;

    public OrderRepository() {
        this.menuBoard = new MenuBoard();
    }

    public boolean isExistMenuName(String name) {
        return menuBoard.getMenuItems(name) != null;
    }

    public MenuItem getMenuItemByName(String name) {
        return menuBoard.getMenuItems(name);
    }
}
