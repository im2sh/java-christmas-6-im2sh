package christmas.view;

import static christmas.view.utils.EventMessage.BENEFIT_DETAIL;
import static christmas.view.utils.EventMessage.EVENT_GUIDE;
import static christmas.view.utils.EventMessage.GIFT_MENTION;
import static christmas.view.utils.EventMessage.NOTING_EVENT;
import static christmas.view.utils.EventMessage.ORDER_RESULT;
import static christmas.view.utils.EventMessage.OUTPUT_AMOUNT;
import static christmas.view.utils.EventMessage.OUTPUT_ORDER;

import christmas.domain.EventName;
import christmas.response.EventResponse;
import christmas.response.OrderHistoryResponse;
import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicBoolean;

public class OutputView {
    private final static DecimalFormat decimalFormat = new DecimalFormat("###,###");

    public static void printErrorMessage(Exception exception) {
        System.out.println((exception.getMessage() + "\n"));
    }

    public static void printOrderList(OrderHistoryResponse orderHistoryResponse) {
        System.out.println(EVENT_GUIDE.getMessage());
        System.out.println(OUTPUT_ORDER.getMessage());
        orderHistoryResponse.getOrder().forEach((name, quantity) ->
                System.out.println(String.format(ORDER_RESULT.getMessage(), name, quantity))
        );
    }

    public static void printOrderAmount(OrderHistoryResponse orderHistoryResponse) {
        System.out.println(OUTPUT_AMOUNT.getMessage());
        System.out.println(decimalFormat.format(orderHistoryResponse.getAmount()));
    }

    public static void printGiftEvent(boolean giftEvent) {
        System.out.println(GIFT_MENTION.getMessage());
        if (giftEvent) {
            System.out.println("샴페인 1개");
        }
        if (!giftEvent) {
            System.out.println(NOTING_EVENT.getMessage());
        }
    }

    public static void printEvent(EventResponse eventResponse) {
        System.out.println(BENEFIT_DETAIL.getMessage());
        AtomicBoolean nothing = new AtomicBoolean(true);

        eventResponse.getEvent().stream()
                .flatMap(eventDetail -> eventDetail.getDetail().entrySet().stream())
                .filter(entry -> !entry.getKey().equals(EventName.NOTING.getEventName()))
                .peek(entry -> nothing.set(false))
                .forEach(entry -> System.out.println(entry.getKey() + ": " + decimalFormat.format(entry.getValue())));

        if (nothing.get()) {
            System.out.println(NOTING_EVENT.getMessage());
        }
    }
}
