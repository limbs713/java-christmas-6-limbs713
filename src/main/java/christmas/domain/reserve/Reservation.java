package christmas.domain.reserve;

import christmas.Constant.EventBadge;
import christmas.domain.order.MenuItem;
import java.util.Map;

public class Reservation {
    private final Receipt receipt;
    private final DiscountBenefits discountBenefits;

    public Reservation(Map<MenuItem,Integer> orderMenu, int reservationDate) {
        this.receipt = Receipt.create(orderMenu);
        this.discountBenefits = DiscountBenefits.of(this.receipt, reservationDate);
    }

    public Map<String, Integer> getOrderReceipt() {
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

    public String getEventBadge() {
        int totalBenefitPrice = discountBenefits.getTotalBenefitPrice();
        EventBadge badge = EventBadge.getBadge(totalBenefitPrice);
        return badge.getName();
    }
}
