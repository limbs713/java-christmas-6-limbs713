package christmas.service;

import static Constant.Message.INPUT_ORDER_MENU_ERROR_MESSAGE;

import Constant.MenuType;
import christmas.domain.order.MenuItem;
import christmas.repository.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService() {
        this.orderRepository = new OrderRepository();
    }

    public void isValidMenu(String orderMenu) {
        List<String> orderMenuNames = List.of(orderMenu.split(","));

        isExistMenuName(orderMenuNames);
        isDuplicateMenu(orderMenuNames);
        isOnlyBeverages(orderMenuNames);
    }

    private void isExistMenuName(List<String> orderMenuNames) {
        boolean containsNotExistMenuName = orderMenuNames.stream()
                .anyMatch(name -> !orderRepository.isExistMenuName(name));

        if(containsNotExistMenuName) {
            throw new IllegalArgumentException(INPUT_ORDER_MENU_ERROR_MESSAGE.getMessage());
        }
    }

    private void isOnlyBeverages(List<String> orderMenuNames) {
        boolean containsNonBeverages = orderMenuNames.stream()
                .map(MenuType::getType)
                .anyMatch(type -> !type.equals(MenuType.BEVERAGES));

        if (!containsNonBeverages) {
            throw new IllegalArgumentException(INPUT_ORDER_MENU_ERROR_MESSAGE.getMessage());
        }
    }

    private void isDuplicateMenu(List<String> orderMenuNames) {
        if (orderMenuNames.stream().distinct().count() != orderMenuNames.size()) {
            throw new IllegalArgumentException(INPUT_ORDER_MENU_ERROR_MESSAGE.getMessage());
        }
    }

    public List<MenuItem> createOrder(String orderMenu) {
        List<String> orderMenuNames = List.of(orderMenu.split(","));

        return orderMenuNames.stream()
                .map(orderRepository::getMenuItemByName)
                .collect(Collectors.toList());
    }
}
