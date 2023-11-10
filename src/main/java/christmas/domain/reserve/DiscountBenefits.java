package christmas.domain.reserve;

import christmas.Constant.DayType;
import christmas.Constant.Days;
import christmas.Constant.MenuType;
import christmas.domain.reserve.Benefit.BonusDishBenefit;
import christmas.domain.reserve.Benefit.ChristmasBenefit;
import christmas.domain.reserve.Benefit.DayTypeBenefit;
import christmas.domain.reserve.Benefit.DiscountBenefit;
import christmas.domain.reserve.Benefit.SpecialBenefit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DiscountBenefits {
    private final List<DiscountBenefit> discountBenefits;

    private DiscountBenefits(Receipt receipt, int reservationDate) {
        discountBenefits = new ArrayList<>();
        addDiscountBenefits(receipt, reservationDate);
    }

    public static DiscountBenefits of(Receipt receipt, int reservationDate) {
        return new DiscountBenefits(receipt, reservationDate);
    }

    private void addDiscountBenefits(Receipt receipt, int reservationDate) {
        checkDiscountByDayType(receipt, reservationDate).ifPresent(discountBenefits::add);
        checkDiscountByStar(reservationDate).ifPresent(discountBenefits::add);
        checkDiscountByTotalPrice(receipt.getTotalPrice()).ifPresent(discountBenefits::add);
        checkDiscountByChristMas(reservationDate).ifPresent(discountBenefits::add);
    }

    private Optional<DiscountBenefit> checkDiscountByDayType(Receipt receipt, int reservationDate) {
        DayType daytype = DayType.valueOf(reservationDate);

        if (daytype.equals(DayType.WEEKDAY)) {
            return Optional.of(new DayTypeBenefit(receipt.getDishesByMenuType(MenuType.DESERT), 2023, DayType.WEEKDAY));
        }

        if (daytype.equals(DayType.WEEKEND)) {
            return Optional.of(new DayTypeBenefit(receipt.getDishesByMenuType(MenuType.MAIN), 2023, DayType.WEEKEND));
        }

        return Optional.empty();
    }

    private Optional<DiscountBenefit> checkDiscountByStar(int reservationDate) {
        if (reservationDate == 25 || Days.valueOf(reservationDate).equals(Days.FRIDAY)) {
            return Optional.of(new SpecialBenefit(1000));
        }

        return Optional.empty();
    }

    private Optional<DiscountBenefit> checkDiscountByTotalPrice(int totalPrice) {
        if (totalPrice >= 120000) {
            return Optional.of(new BonusDishBenefit(25000));
        }

        return Optional.empty();
    }

    public Optional<DiscountBenefit> checkDiscountByChristMas(int reservationDate) {
        if (reservationDate >= 25) {
            return Optional.of(new ChristmasBenefit((25 - reservationDate) * 100));
        }

        return Optional.empty();
    }

    public int getTotalBenefitPrice() {
        return discountBenefits.stream()
                .mapToInt(DiscountBenefit::getBenefitPrice)
                .sum();
    }

    public int getTotalDiscountPrice() {
        return discountBenefits.stream()
                .filter(discountBenefits -> !discountBenefits.isBonusBenefit())
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
}
