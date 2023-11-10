package christmas.domain.reserve.Benefit;

public abstract class DiscountBenefit {
    int benefitPrice;

    public DiscountBenefit(int benefitPrice) {
        this.benefitPrice = benefitPrice;
    }
    public abstract int getBenefitPrice();

    public abstract boolean isBonusBenefit();

    public abstract String getBenefitName();
}
