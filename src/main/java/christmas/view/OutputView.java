package christmas.view;

import static christmas.view.utils.EventMessage.EVENT_GUIDE;
import static christmas.view.utils.EventMessage.ORDER_RESULT;
import static christmas.view.utils.EventMessage.OUTPUT_ORDER;

import christmas.response.OrderHistoryResponse;

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
}
