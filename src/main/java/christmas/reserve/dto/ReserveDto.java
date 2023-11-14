package christmas.reserve.dto;

import java.util.Map;

public class ReserveDto {
    private int reserveDate;
    private Map<String, Integer> orderMenu;
    private int TotalPriceBeforeDiscount;
    private boolean BonusMenu;
    private Map<String, Integer> benefits;
    private int TotalBenefitPrice;
    private int TotalPriceAfterDiscount;
    private String EventBadge;


    public Map<String, Integer> getOrderMenu() {
        return orderMenu;
    }

    public void setOrderMenu(Map<String, Integer> orderMenu) {
        this.orderMenu = orderMenu;
    }

    public int getTotalPriceBeforeDiscount() {
        return TotalPriceBeforeDiscount;
    }

    public void setTotalPriceBeforeDiscount(int totalPriceBeforeDiscount) {
        TotalPriceBeforeDiscount = totalPriceBeforeDiscount;
    }

    public boolean getBonusMenu() {
        return BonusMenu;
    }

    public void setBonusMenu(boolean bonusMenu) {
        BonusMenu = bonusMenu;
    }

    public Map<String, Integer> getBenefits() {
        return benefits;
    }

    public void setBenefits(Map<String, Integer> benefits) {
        this.benefits = benefits;
    }

    public int getTotalBenefitPrice() {
        return TotalBenefitPrice;
    }

    public void setTotalBenefitPrice(int totalBenefitPrice) {
        TotalBenefitPrice = totalBenefitPrice;
    }

    public int getTotalPriceAfterDiscount() {
        return TotalPriceAfterDiscount;
    }

    public void setTotalPriceAfterDiscount(int totalPriceAfterDiscount) {
        TotalPriceAfterDiscount = totalPriceAfterDiscount;
    }

    public String getEventBadge() {
        return EventBadge;
    }

    public void setEventBadge(String eventBadge) {
        EventBadge = eventBadge;
    }

    public int getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(int reserveDate) {
        this.reserveDate = reserveDate;
    }
}
