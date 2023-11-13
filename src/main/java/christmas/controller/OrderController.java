package christmas.controller;

import static christmas.Constant.Message.*;

import christmas.Constant.Message;
import christmas.dto.OrderDto;
import christmas.repository.OrderRepository;
import christmas.service.OrderService;
import christmas.Validator.MenuValidator;
import christmas.Validator.ReservationDateValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OrderController {
    private final OrderService orderService;

    public OrderController() {
        orderService = new OrderService(new OrderRepository());
    }

    public OrderDto order() {
        OutputView.printMessage(WELLCOME_TITLE_MESSAGE.getMessage());

        String reservationDate = new InputBuilder(InputView::input, ReservationDateValidator::isValid,
                INPUT_RESERVATION_DATE_TITLE_MESSAGE)
                .build();
        String orderMenu = new InputBuilder(InputView::input, MenuValidator::isValid, INPUT_ORDER_TITLE_MESSAGE)
                .menuValidator(orderService::isValidMenu)
                .build();

        return new OrderDto(orderService.createOrder(orderMenu), reservationDate);
    }

    private static class InputBuilder {
        private final Supplier<String> inputView;
        private final Consumer<String> inputValidator;
        private final Message message;

        private Consumer<String> menuValidator = null;

        public InputBuilder(Supplier<String> inputView, Consumer<String> inputValidator, Message message) {
            this.inputView = inputView;
            this.inputValidator = inputValidator;
            this.message = message;
        }

        public InputBuilder menuValidator(Consumer<String> menuValidator) {
            this.menuValidator = menuValidator;
            return this;
        }

        public String build() {
            try {
                OutputView.printMessage(message.getMessage());

                String input = inputView.get();
                inputValidator.accept(input);
                if (menuValidator != null) {
                    menuValidator.accept(input);
                }
                return input;
            } catch (IllegalArgumentException e) {
                OutputView.printMessage(e.getMessage());
                return build();
            }
        }
    }
}
