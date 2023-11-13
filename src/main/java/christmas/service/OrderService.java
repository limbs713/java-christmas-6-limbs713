package christmas.service;

import static christmas.Constant.Message.INPUT_ORDER_MENU_ERROR_MESSAGE;

import christmas.Constant.MenuType;
import christmas.Constant.RegEx;
import christmas.domain.order.MenuItem;
import christmas.repository.OrderRepository;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderService {
    private static final int QUANTITY_LIMIT = 20;
    private final OrderRepository orderRepository;
    private final Pattern orderMenuParser = RegEx.PARSING_MENU_REG_EX.getRegExPattern();

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository =  orderRepository;
    }

    public void isValidMenu(String orderMenu) {
        Matcher matcher = orderMenuParser.matcher(orderMenu);
        List<String> orderMenuNames = parseOnlyName(matcher);

        isExistMenuName(orderMenuNames);
        isDuplicateMenu(orderMenuNames);
        isOnlyBeverages(orderMenuNames);
        isQuantityUnderTwenty(parseOnlyQuantity(matcher));
    }

    private void isQuantityUnderTwenty(int totalQuantity) {
        if(totalQuantity > QUANTITY_LIMIT){
            throw new IllegalArgumentException(INPUT_ORDER_MENU_ERROR_MESSAGE.getMessage());
        }
    }

    private int parseOnlyQuantity(Matcher matcher) {
        int totalOrderQuantity = 0;

        while (matcher.find()) {
            totalOrderQuantity += Integer.parseInt(matcher.group(2));
        }

        return totalOrderQuantity;
    }

    private List<String> parseOnlyName(Matcher matcher) {
        List<String> orderMenuNames = new ArrayList<>();

        while(matcher.find()) {
            orderMenuNames.add(matcher.group(1));
        }

        return orderMenuNames;
    }

    private void isExistMenuName(List<String> orderMenuNames) {
        boolean containsNotExistMenuName = orderMenuNames.stream()
                .anyMatch(name -> !orderRepository.isExistMenuName(name));

        if (containsNotExistMenuName) {
            throw new IllegalArgumentException(INPUT_ORDER_MENU_ERROR_MESSAGE.getMessage());
        }
    }

    private void isOnlyBeverages(List<String> orderMenuNames) {
        boolean containsNonBeverages = orderMenuNames.stream()
                .map(MenuType::getType)
                .anyMatch(type -> type.isPresent() && !type.get().equals(MenuType.BEVERAGES));

        if (!containsNonBeverages) {
            throw new IllegalArgumentException(INPUT_ORDER_MENU_ERROR_MESSAGE.getMessage());
        }
    }

    private void isDuplicateMenu(List<String> orderMenuNames) {
        if (orderMenuNames.stream().distinct().count() != orderMenuNames.size()) {
            throw new IllegalArgumentException(INPUT_ORDER_MENU_ERROR_MESSAGE.getMessage());
        }
    }

    public Map<MenuItem, Integer> createOrder(String orderMenu) {
        Matcher matcher = orderMenuParser.matcher(orderMenu);
        Map<MenuItem, Integer> menuNameWithCount = new LinkedHashMap<>();

        while(matcher.find()){
            MenuItem menuItem = orderRepository.getMenuItemByName(matcher.group(1));
            int count = Integer.parseInt(matcher.group(2));
            menuNameWithCount.put(menuItem, count);
        }

        return menuNameWithCount;
    }
}
