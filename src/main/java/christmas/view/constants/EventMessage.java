package christmas.view.constants;

public enum EventMessage {
    INTRODUCTION("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    INPUT_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    INPUT_ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    EVENT_GUIDE("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n"),
    OUTPUT_ORDER("<주문 메뉴>"),

    OUTPUT_AMOUNT("\n<할인 전 총주문 금액>"),
    GIFT_MENTION("\n<증정 메뉴>"),
    ONE_CHAMPAGNE("샴페인 1개"),
    COLON(": "),
    NOTING_EVENT("없음"),
    BENEFIT_DETAIL("\n<혜택 내역>"),
    UNIT("원"),
    DISCOUNT_BENEFIT("\n<총혜택 금액>"),
    PAYMENT_AMOUNT("\n<할인 후 예상 결제 금액>"),
    DECEMBER_BADGE("\n<12월 이벤트 배지>"),
    ORDER_RESULT("%s %d개");
    private final String message;

    EventMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
