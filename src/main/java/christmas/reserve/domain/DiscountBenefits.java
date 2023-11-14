package christmas.reserve.domain;

import christmas.constant.DayType;
import christmas.constant.Days;
import christmas.constant.MenuType;
import christmas.reserve.domain.Benefit.BonusDishBenefit;
import christmas.reserve.domain.Benefit.ChristmasBenefit;
import christmas.reserve.domain.Benefit.DayTypeBenefit;
import christmas.reserve.domain.Benefit.DiscountBenefit;
import christmas.reserve.domain.Benefit.SpecialBenefit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DiscountBenefits {
    private static final int CHRISTMAS = 25;
    private static final int BENEFIT_PRICE_DAY_TYPE = 2023;
    private static final int BENEFIT_PRICE_STAR_DAY = 1000;
    private static final int BENEFIT_PRICE_BONUS_DISH = 25000;
    private static final int BENEFIT_PRICE_CHRISTMAS = 100;
    private static final int CONDITION_FOR_BONUS_DISH = 120000;
    private static final int CONDITION_FOR_EVENT = 10000;
    private static final int START_DISCOUNT_DAY = 1;
    private final List<DiscountBenefit> discountBenefits;

    private DiscountBenefits(Receipt receipt, int reservationDate) {
        discountBenefits = new ArrayList<>();
        addDiscountBenefits(receipt, reservationDate);
    }

    public static DiscountBenefits of(Receipt receipt, int reservationDate) {
        return new DiscountBenefits(receipt, reservationDate);
    }

    private void addDiscountBenefits(Receipt receipt, int reservationDate) {
        if (receipt.getTotalPrice() >= CONDITION_FOR_EVENT) {
            checkDiscountByDayType(receipt, reservationDate).ifPresent(discountBenefits::add);
            checkDiscountByStar(reservationDate).ifPresent(discountBenefits::add);
            checkDiscountByTotalPrice(receipt.getTotalPrice()).ifPresent(discountBenefits::add);
            checkDiscountByChristMas(reservationDate).ifPresent(discountBenefits::add);
        }
    }

    private Optional<DiscountBenefit> checkDiscountByDayType(Receipt receipt, int reservationDate) {
        DayType daytype = DayType.valueOf(reservationDate);
        int benefitMenuCount;

        if (daytype.equals(DayType.WEEKDAY) && (benefitMenuCount = receipt.getDishesByMenuType(MenuType.DESERT)) != 0) {
            return Optional.of(new DayTypeBenefit(benefitMenuCount, BENEFIT_PRICE_DAY_TYPE,
                    DayType.WEEKDAY));
        }

        if (daytype.equals(DayType.WEEKEND) && (benefitMenuCount = receipt.getDishesByMenuType(MenuType.MAIN)) != 0) {
            return Optional.of(new DayTypeBenefit(benefitMenuCount, BENEFIT_PRICE_DAY_TYPE,
                    DayType.WEEKEND));
        }

        return Optional.empty();
    }

    private Optional<DiscountBenefit> checkDiscountByStar(int reservationDate) {
        if (reservationDate == CHRISTMAS || Days.valueOf(reservationDate).equals(Days.SUNDAY)) {
            return Optional.of(new SpecialBenefit(BENEFIT_PRICE_STAR_DAY));
        }

        return Optional.empty();
    }

    private Optional<DiscountBenefit> checkDiscountByTotalPrice(int totalPrice) {
        if (totalPrice >= CONDITION_FOR_BONUS_DISH) {
            return Optional.of(new BonusDishBenefit(BENEFIT_PRICE_BONUS_DISH));
        }

        return Optional.empty();
    }

    public Optional<DiscountBenefit> checkDiscountByChristMas(int reservationDate) {
        if (reservationDate <= CHRISTMAS) {
            return Optional.of(new ChristmasBenefit((reservationDate - START_DISCOUNT_DAY) * BENEFIT_PRICE_CHRISTMAS
            ));
        }

        return Optional.empty();
    }

    public int getTotalBenefitPrice() {
        return discountBenefits.stream()
                .mapToInt(DiscountBenefit::getBenefitPrice)
                .sum();
    }

    public boolean isBonusExist() {
        return discountBenefits.stream()
                .anyMatch(DiscountBenefit::isBonusBenefit);
    }

    public Map<String, Integer> getBenefitSpecification() {
        return discountBenefits.stream()
                .collect(Collectors.toMap(DiscountBenefit::getBenefitName, DiscountBenefit::getBenefitPrice));
    }

    public int getActualDiscountPrice() {
        return discountBenefits.stream()
                .filter(benefit -> !benefit.isBonusBenefit())
                .mapToInt(DiscountBenefit::getBenefitPrice)
                .sum();
    }
}
