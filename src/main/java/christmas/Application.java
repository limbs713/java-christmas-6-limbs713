package christmas;

import christmas.controller.OrderController;
import christmas.controller.ReserveController;
import christmas.dto.OrderDto;

public class Application {
    public static void main(String[] args) {
        OrderController orderController = new OrderController();
        ReserveController reserveController = new ReserveController();

        OrderDto orderDto = orderController.order();
        reserveController.reserve(orderDto);
    }
}
