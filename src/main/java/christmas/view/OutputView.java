package christmas.view;

import static christmas.domain.constants.EventDiscount.ZERO;
import static christmas.view.constants.EventMessage.BENEFIT_DETAIL;
import static christmas.view.constants.EventMessage.COLON;
import static christmas.view.constants.EventMessage.DECEMBER_BADGE;
import static christmas.view.constants.EventMessage.DISCOUNT_BENEFIT;
import static christmas.view.constants.EventMessage.EVENT_GUIDE;
import static christmas.view.constants.EventMessage.GIFT_MENTION;
import static christmas.view.constants.EventMessage.NOTING_EVENT;
import static christmas.view.constants.EventMessage.ONE_CHAMPAGNE;
import static christmas.view.constants.EventMessage.ORDER_RESULT;
import static christmas.view.constants.EventMessage.OUTPUT_AMOUNT;
import static christmas.view.constants.EventMessage.OUTPUT_ORDER;
import static christmas.view.constants.EventMessage.PAYMENT_AMOUNT;
import static christmas.view.constants.EventMessage.UNIT;
import static christmas.view.constants.EventMessage.printMessage;

import christmas.domain.constants.Badge;
import christmas.domain.constants.EventName;
import christmas.response.EventResponse;
import christmas.response.OrderHistoryResponse;
import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicBoolean;

public class OutputView {
    private final static DecimalFormat decimalFormat = new DecimalFormat("###,###");

    public static void printErrorMessage(Exception exception) {
        printMessage((exception.getMessage() + "\n"));
    }

    public static void printOrderList(OrderHistoryResponse orderHistoryResponse) {
        printMessage(EVENT_GUIDE.getMessage());
        printMessage(OUTPUT_ORDER.getMessage());
        orderHistoryResponse.getOrder().forEach((name, quantity) ->
                printMessage(String.format(ORDER_RESULT.getMessage(), name, quantity))
        );
    }

    public static void printOrderAmount(OrderHistoryResponse orderHistoryResponse) {
        printMessage(OUTPUT_AMOUNT.getMessage());
        printMessage(decimalFormat.format(orderHistoryResponse.getAmount()));
    }

    public static void printGiftEvent(boolean giftEvent) {
        System.out.println(GIFT_MENTION.getMessage());
        if (giftEvent) {
            printMessage(ONE_CHAMPAGNE.getMessage());
        }
        if (!giftEvent) {
            printMessage(NOTING_EVENT.getMessage());
        }
    }

    public static void printEvent(EventResponse eventResponse) {
        printMessage(BENEFIT_DETAIL.getMessage());
        AtomicBoolean nothing = new AtomicBoolean(true);

        eventResponse.getEvent().stream()
                .flatMap(eventDetail -> eventDetail.getDetail().entrySet().stream())
                .filter(entry -> !entry.getKey().equals(EventName.NOTING.getEventName()))
                .peek(entry -> nothing.set(false))
                .forEach(entry -> printMessage(
                        entry.getKey() + COLON.getMessage() + decimalFormat.format(entry.getValue())
                                + UNIT.getMessage()));

        if (nothing.get()) {
            printMessage(NOTING_EVENT.getMessage());
        }
    }

    public static void printDiscountAmount(int calculateDiscount) {
        printMessage(DISCOUNT_BENEFIT.getMessage());
        if (calculateDiscount == ZERO.getDiscountMoney()) {
            printMessage(decimalFormat.format(calculateDiscount) + UNIT.getMessage());
        }
        if (calculateDiscount > ZERO.getDiscountMoney()) {
            printMessage("-" + decimalFormat.format(calculateDiscount) + UNIT.getMessage());
        }
    }

    public static void printExpectedPaymentAmount(int calculateExpectedPaymentAmount) {
        printMessage(PAYMENT_AMOUNT.getMessage());
        printMessage(decimalFormat.format(calculateExpectedPaymentAmount) + UNIT.getMessage());
    }

    public static void printUserBadge(Badge userBadge) {
        printMessage(DECEMBER_BADGE.getMessage());
        printMessage(userBadge.getName());
    }
}
