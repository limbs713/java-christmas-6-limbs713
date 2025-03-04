package christmas.reserve.repository;

import christmas.reserve.domain.Reservation;
import christmas.order.dto.OrderDto;
import java.util.Map;

public class ReserveRepository {
    private Reservation reservation;

    public void save(OrderDto orderDto) {
        this.reservation = new Reservation(orderDto.getOrderMenu(), orderDto.getReservationDate());
    }

    public int getReservationDate() {
        return reservation.getReservationDate();
    }

    public Map<String, Integer> getReservations() {
        return reservation.getOrderReceipt();
    }

    public int getTotalOrderPrice() {
        return reservation.getTotalOrderPrice();
    }

    public boolean isBonusMenuExist() {
        return reservation.isBonusBenefitExist();
    }

    public Map<String, Integer> getBenefitSpecification() {
        return reservation.getBenefitSpecification();
    }

    public int getTotalBenefitPrice() {
        return reservation.getTotalBenefitPrice();
    }

    public int getFinalOrderPrice() {
        return reservation.getTotalOrderPrice() - reservation.getActualDiscountPrice();
    }

    public String getEventBadge() {
        return reservation.getEventBadge();
    }
}
