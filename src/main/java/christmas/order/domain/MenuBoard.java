package christmas.order.domain;

import java.util.ArrayList;
import java.util.List;

public class MenuBoard {
    private final List<MenuItem> menuItems;

    public MenuBoard() {
        menuItems = new ArrayList<>();
        menuItems.add(MenuItem.of("양송이수프", 6000));
        menuItems.add(MenuItem.of("타파스", 5500));
        menuItems.add(MenuItem.of("시저샐러드", 8000));
        menuItems.add(MenuItem.of("티본스테이크", 55000));
        menuItems.add(MenuItem.of("바비큐립", 54000));
        menuItems.add(MenuItem.of("해산물파스타", 35000));
        menuItems.add(MenuItem.of("크리스마스파스타", 25000));
        menuItems.add(MenuItem.of("초코케이크", 15000));
        menuItems.add(MenuItem.of("아이스크림", 5000));
        menuItems.add(MenuItem.of("제로콜라", 3000));
        menuItems.add(MenuItem.of("레드와인", 60000));
        menuItems.add(MenuItem.of("샴페인", 25000));
    }

    public MenuItem getMenuItems(String name) {
        return menuItems.stream()
                .filter(menuItem -> menuItem.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
