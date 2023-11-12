package christmas.view;

import static christmas.view.utils.EventMessage.BENEFIT_DETAIL;
import static christmas.view.utils.EventMessage.EVENT_GUIDE;
import static christmas.view.utils.EventMessage.GIFT_MENTION;
import static christmas.view.utils.EventMessage.NOTING_EVENT;
import static christmas.view.utils.EventMessage.ORDER_RESULT;
import static christmas.view.utils.EventMessage.OUTPUT_AMOUNT;
import static christmas.view.utils.EventMessage.OUTPUT_ORDER;

import christmas.domain.Event;
import christmas.domain.EventDetail;
import christmas.response.EventResponse;
import christmas.response.OrderHistoryResponse;
import christmas.view.utils.EventMessage;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
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
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
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
        boolean nothing = true;

        for(EventDetail eventDetail : eventResponse.getEvent()){
            Map<String, Integer> eventDetailMap = eventDetail.getEventDetail();

            for (Map.Entry<String, Integer> entry : eventDetailMap.entrySet()) {
                String eventName = entry.getKey();
                int discountAmount = entry.getValue();

                if (discountAmount != 0) {
                    nothing = false;
                    System.out.println(eventName + ": " + formatDiscount(discountAmount));
                }
            }
        }

        if (nothing) {
            System.out.println(NOTING_EVENT.getMessage());
        }
    }

    private static String formatDiscount(int discountAmount) {
        return String.format("-%,d원", Math.abs(discountAmount));
    }
}
