package christmas.reserve.controller;

import christmas.order.dto.OrderDto;
import christmas.reserve.dto.ReserveDto;
import christmas.reserve.service.ReserveService;
import christmas.view.OutputView;

public class ReserveController {
    private final ReserveService reserveService;

    public ReserveController() {
        this.reserveService = new ReserveService();
    }

    public void reserve(OrderDto orderDto) {
        reserveService.makeReservation(orderDto);
        ReserveDto reserveDto = reserveService.getReserveDto();

        OutputView.printReservation(reserveDto);
    }
}
