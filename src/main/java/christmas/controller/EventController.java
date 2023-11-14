package christmas.controller;

import christmas.domain.Event;
import christmas.domain.FoodOrder;
import christmas.domain.User;
import christmas.request.FoodOrderRequest;
import christmas.response.EventResponse;
import christmas.service.EventService;
import christmas.validator.DateInputValidator;
import christmas.validator.OrderInputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

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
        showOrderBill(foodOrder);
        Event event = occursEvent(user, foodOrder);
        showEventBenefits(event);
        occurBenefits(user, foodOrder, event);
    }

    private void showOrderBill(FoodOrder foodOrder) {
        OrderController orderController = new OrderController(foodOrder);
        orderController.showOrderHistory();
        orderController.showOrderAmount();
    }

    public Event occursEvent(User user, FoodOrder foodOrder) {
        EventService eventService = new EventService(user, foodOrder);
        printGiftEvent(eventService.isExistsGift());
        return eventService.occurEvent();
    }

    private void showEventBenefits(Event event) {
        EventResponse eventResponse = event.toResponse();
        printEvent(eventResponse);
    }

    private void occurBenefits(User user, FoodOrder foodOrder, Event event) {
        BenefitController benefitController = new BenefitController(user, foodOrder, event);
        benefitController.printBenefitAmount();
        benefitController.printFinalPaymentAmount();
        benefitController.evaluateUserBadge();
        benefitController.printUserBadge();
    }

    private void printGiftEvent(boolean giftEvent) {
        OutputView.printGiftEvent(giftEvent);
    }

    private void printEvent(EventResponse eventResponse) {
        OutputView.printEvent(eventResponse);
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
