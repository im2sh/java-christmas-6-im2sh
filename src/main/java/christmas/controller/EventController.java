package christmas.controller;

import christmas.domain.Event;
import christmas.domain.FoodOrder;
import christmas.domain.User;
import christmas.request.FoodOrderRequest;
import christmas.service.EventService;
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
        occursEvent(user,foodOrder);
    }

    public void showOrderBill(OrderController orderController) {
        orderController.showOrderHistory();
        orderController.showOrderAmount();
    }

    public void occursEvent(User user, FoodOrder foodOrder){
        EventService eventService = new EventService(user, foodOrder);
        occurGiftEvent(eventService.isExistsGift());
    }

    private void occurGiftEvent(boolean giftEvent){
        OutputView.printGiftEvent(giftEvent);
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
