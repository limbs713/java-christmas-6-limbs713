package christmas.domain.order;

public class MenuItem {
    private final String name;
    private final int price;

    private MenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static MenuItem of(String name, int price) {
        return new MenuItem(name, price);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
