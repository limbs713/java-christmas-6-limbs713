package christmas.reserve.domain.Benefit;

public class SpecialBenefit extends DiscountBenefit {

    public SpecialBenefit(int benefitPrice){
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
        return "특별 할인";
    }
}
