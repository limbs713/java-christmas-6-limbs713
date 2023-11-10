package christmas.domain.reserve;

import christmas.Constant.EventBadge;
import christmas.domain.order.MenuItem;
import java.util.List;
import java.util.Map;

public class Reservation {
    private final Receipt receipt;
    private final DiscountBenefits discountBenefits;

    public Reservation(List<MenuItem> menuItems, int reservationDate) {
        this.receipt = Receipt.create(menuItems);
        this.discountBenefits = DiscountBenefits.of(this.receipt, reservationDate);
    }

    public Map<String, Long> getOrderReceipt() {
        return receipt.getOrderReceipt();
    }

    public int getTotalOrderPrice() {
        return receipt.getTotalPrice();
    }

    public boolean isBonusBenefitExist() {
        return discountBenefits.isBonusExist();
    }

    public Map<String, Integer> getBenefitSpecification() {
        return discountBenefits.getBenefitSpecification();
    }

    public int getTotalBenefitPrice() {
        return discountBenefits.getTotalBenefitPrice();
    }

    public int getTotalDiscountPrice() {
        return discountBenefits.getTotalDiscountPrice();
    }

    public String getEventBadge() {
        int totalBenefitPrice = discountBenefits.getTotalBenefitPrice();
        EventBadge badge = EventBadge.getBadge(totalBenefitPrice);
        return badge.getName();
    }
}
