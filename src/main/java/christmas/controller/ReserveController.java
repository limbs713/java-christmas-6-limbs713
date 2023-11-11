package christmas.controller;

import christmas.dto.OrderDto;
import christmas.dto.ReserveDto;
import christmas.service.ReserveService;
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
