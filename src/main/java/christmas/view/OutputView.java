package christmas.view;

import static christmas.Constant.Message.*;

import christmas.dto.ReserveDto;
import java.util.Map;

public class OutputView {

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printReservation(ReserveDto reserveDto) {
        printMessage(String.format(BENEFIT_TITLE_MESSAGE.getMessage(),reserveDto.getReserveDate()));

        printOrderMenu(reserveDto.getOrderMenu());
        printOrderPriceBeforeDiscount(reserveDto.getTotalPriceBeforeDiscount());
        printBonusMenu(reserveDto.getBonusMenu());
        printBenefits(reserveDto.getBenefits());
        printBenefitPrice(reserveDto.getTotalBenefitPrice());
        printOrderPriceAfterDiscount(reserveDto.getTotalPriceAfterDiscount());
        printEventBadge(reserveDto.getEventBadge());
    }

    private static void printEventBadge(String eventBadge) {
        printMessage(OUTPUT_EVENT_BADGE_TITLE_MESSAGE.getMessage());

        printMessage(eventBadge);
    }

    private static void printOrderPriceAfterDiscount(int totalPriceAfterDiscount) {
        printMessage(OUTPUT_TOTAL_PRICE_AFTER_DISCOUNT_TITLE_MESSAGE.getMessage());

        printMessage(String.format(OUTPUT_TOTAL_PRICE_MESSAGE.getMessage(), totalPriceAfterDiscount));
    }

    private static void printBenefitPrice(int totalBenefitPrice) {
        printMessage(OUTPUT_BENEFIT_PRICE_TITLE_MESSAGE.getMessage());
        printMessage(String.format(OUTPUT_BENEFIT_PRICE_MESSAGE.getMessage(), -1 * totalBenefitPrice));
    }

    private static void printBenefits(Map<String, Integer> benefits) {
        printMessage(OUTPUT_BENEFIT_TITLE_MESSAGE.getMessage());

        if (benefits.isEmpty()) {
            printMessage(OUTPUT_NO_BENEFIT_MESSAGE.getMessage());
        }

        if (!benefits.isEmpty()) {
            benefits.forEach((key, value) ->
                    printMessage(String.format(OUTPUT_BENEFIT_MESSAGE.getMessage(), key, -1 * value)));
        }
    }

    private static void printBonusMenu(boolean isBonusMenuExist) {
        printMessage(OUTPUT_BONUS_MENU_TITLE_MESSAGE.getMessage());

        if (isBonusMenuExist) {
            printMessage(OUTPUT_BONUS_MENU_MESSAGE.getMessage());
        }

        if (!isBonusMenuExist) {
            printMessage(OUTPUT_NO_BENEFIT_MESSAGE.getMessage());
        }
    }

    private static void printOrderPriceBeforeDiscount(int totalPriceBeforeDiscount) {
        printMessage(OUTPUT_TOTAL_PRICE_BEFORE_DISCOUNT_TITLE_MESSAGE.getMessage());

        printMessage(String.format(OUTPUT_TOTAL_PRICE_MESSAGE.getMessage(), totalPriceBeforeDiscount));
    }

    private static void printOrderMenu(Map<String, Integer> orderMenu) {
        printMessage(OUTPUT_ORDER_MENU_TITLE_MESSAGE.getMessage());

        orderMenu.forEach(
                (key, value) -> printMessage(String.format(OUTPUT_ORDER_MENU_MESSAGE.getMessage(), key, value)));
    }
}
