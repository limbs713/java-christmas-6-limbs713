package christmas.dto;

import christmas.domain.order.MenuItem;
import java.util.Map;

public class OrderDto {
    private final Map<MenuItem, Integer> orderMenuItems;
    private final int reservationDate;

    public OrderDto(Map<MenuItem, Integer> orderMenu, String reservationDate) {
        this.orderMenuItems = orderMenu;
        this.reservationDate = Integer.parseInt(reservationDate);
    }

    public Map<MenuItem, Integer> getOrderMenu() {
        return orderMenuItems;
    }

    public int getReservationDate() {
        return reservationDate;
    }

}
