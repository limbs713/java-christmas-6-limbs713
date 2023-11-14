package christmas.reserve.service;

import christmas.order.dto.OrderDto;
import christmas.reserve.dto.ReserveDto;
import christmas.reserve.repository.ReserveRepository;

public class ReserveService {
    private final ReserveRepository reserveRepository;

    public ReserveService() {
        this.reserveRepository = new ReserveRepository();
    }

    public void makeReservation(OrderDto orderDto) {
        reserveRepository.save(orderDto);
    }

    public ReserveDto getReserveDto() {
        ReserveDto reserveDto = new ReserveDto();

        reserveDto.setReserveDate(reserveRepository.getReservationDate());
        reserveDto.setOrderMenu(reserveRepository.getReservations());
        reserveDto.setTotalPriceBeforeDiscount(reserveRepository.getTotalOrderPrice());
        reserveDto.setBonusMenu(reserveRepository.isBonusMenuExist());
        reserveDto.setBenefits(reserveRepository.getBenefitSpecification());
        reserveDto.setTotalBenefitPrice(reserveRepository.getTotalBenefitPrice());
        reserveDto.setTotalPriceAfterDiscount(reserveRepository.getFinalOrderPrice());
        reserveDto.setEventBadge(reserveRepository.getEventBadge());

        return reserveDto;
    }
}
