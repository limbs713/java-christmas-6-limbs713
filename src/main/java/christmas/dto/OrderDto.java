package christmas.dto;

import christmas.domain.order.MenuItem;
import java.util.List;

public class OrderDto {
    private final List<MenuItem> orderMenuItems;
    private final int reservationDate;

    public OrderDto(List<MenuItem> orderMenuNames, String reservationDate) {
        this.orderMenuItems = orderMenuNames;
        this.reservationDate = Integer.parseInt(reservationDate);
    }

    public List<MenuItem> getOrderMenuItems() {
        return orderMenuItems;
    }

    public int getReservationDate() {
        return reservationDate;
    }

}
