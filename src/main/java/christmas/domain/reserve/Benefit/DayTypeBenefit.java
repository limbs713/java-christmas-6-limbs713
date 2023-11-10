package christmas.domain.reserve.Benefit;


import christmas.Constant.DayType;

public class DayTypeBenefit extends DiscountBenefit {
    private final int targetDishCount;
    private final DayType dayType;

    public DayTypeBenefit(int targetDishCount, int benefitPrice, DayType dayType) {
        super(benefitPrice);
        this.targetDishCount = targetDishCount;
        this.dayType = dayType;
    }

    @Override
    public int getBenefitPrice() {
        return targetDishCount * benefitPrice;
    }

    @Override
    public boolean isBonusBenefit() {
        return false;
    }

    @Override
    public String getBenefitName() {
        if (dayType.equals(DayType.WEEKDAY)) {
            return "평일 할인";
        }
        if (dayType.equals(DayType.WEEKEND)) {
            return "주말 할인";
        }

        throw new IllegalArgumentException("[ERROR] 반드시 평일 혹은 주말이여야 합니다.");
    }
}
