package christmas.domain.reserve.Benefit;

public class ChristmasBenefit extends DiscountBenefit {
    private static final int DEFAULT_DISCOUNT_PRICE = 1000;

    public ChristmasBenefit(int benefitPrice) {
        super(benefitPrice + DEFAULT_DISCOUNT_PRICE);
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
