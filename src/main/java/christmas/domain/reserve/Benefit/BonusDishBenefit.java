package christmas.domain.reserve.Benefit;

public class BonusDishBenefit extends DiscountBenefit {

    public BonusDishBenefit(int benefitPrice) {
        super(benefitPrice);
    }

    @Override
    public int getBenefitPrice() {
        return benefitPrice;
    }

    @Override
    public boolean isBonusBenefit() {
        return true;
    }

    @Override
    public String getBenefitName() {
        return "증정 이벤트";
    }
}
