package christmas.Constant;

public enum Message {
    WELLCOME_TITLE_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    INPUT_RESERVATION_DATE_TITLE_MESSAGE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    INPUT_ORDER_TITLE_MESSAGE("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    BENEFIT_TITLE_MESSAGE("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    OUTPUT_ORDER_MENU_TITLE_MESSAGE("<주문 메뉴>"),
    OUTPUT_ORDER_MENU_MESSAGE("%s %d개"),
    OUTPUT_TOTAL_PRICE_BEFORE_DISCOUNT_TITLE_MESSAGE("<할인 전 총주문 금액>"),
    OUTPUT_PRICE_WITH_COMMA_MESSAGE("%,d"),
    OUTPUT_BONUS_BENEFIT_MENU_MESSAGE("<증정 메뉴>"),
    OUTPUT_TOTAL_BENEFIT_TITLE_MESSAGE("<혜택 내역>"),
    OUTPUT_TOTAL_BENEFIT_PRICE_TITLE_MESSAGE("<총혜택 금액>"),
    OUTPUT_TOTAL_PRICE_AFTER_DISCOUNT_TITLE_MESSAGE("<할인 후 예상 결제 금액>"),
    OUTPUT_EVENT_BADGE_TITLE_MESSAGE("<12월 이벤트 배지>");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
