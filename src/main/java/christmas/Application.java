package christmas;

import christmas.order.controller.OrderController;
import christmas.reserve.controller.ReserveController;
import christmas.order.dto.OrderDto;

public class Application {
    public static void main(String[] args) {
        OrderController orderController = new OrderController();
        ReserveController reserveController = new ReserveController();

        OrderDto orderDto = orderController.order();
        reserveController.reserve(orderDto);
    }
}
