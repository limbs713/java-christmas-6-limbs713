package christmas.domain.reserve.Benefit;

public class ChristmasBenefit extends DiscountBenefit {
    public ChristmasBenefit(int benefitPrice) {
        super(benefitPrice);
    }

    @Override
    public int getBenefitPrice() {
        return benefitPrice;
    }

    @Override
    public boolean isBonusBenefit() {
        return false;
    }

    @Override
    public String getBenefitName() {
        return "크리스마스 디데이 할인";
    }
}
