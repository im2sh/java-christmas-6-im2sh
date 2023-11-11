package christmas.controller;

import christmas.domain.FoodOrder;
import christmas.domain.User;
import christmas.request.FoodOrderRequest;
import christmas.validator.DateInputValidator;
import christmas.validator.OrderInputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class EventController {
    private final DateInputValidator dateInputValidator;
    private final OrderInputValidator orderInputValidator;

    public EventController(DateInputValidator dateInputValidator, OrderInputValidator orderInputValidator) {
        this.dateInputValidator = dateInputValidator;
        this.orderInputValidator = orderInputValidator;
    }

    public void event() {
        User user = new User(inputReservationDate());
        FoodOrder foodOrder = new FoodOrder(inputOrder());
        showOrderBill(new OrderController(foodOrder));
    }

    public void showOrderBill(OrderController orderController) {
        orderController.showOrderHistory();
        orderController.showOrderAmount();
    }

    private int inputReservationDate() {
        while (true) {
            try {
                return dateInputValidator.validateInputDate(InputView.inputReservationDate());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }

    private FoodOrderRequest inputOrder() {
        while (true) {
            try {
                return orderInputValidator.parseOrders(InputView.inputOrder());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }

}
