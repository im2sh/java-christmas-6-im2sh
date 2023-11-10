package christmas;

import christmas.controller.EventController;
import christmas.validator.DateInputValidator;
import christmas.validator.OrderInputValidator;

public class Application {
    public static void main(String[] args) {
        DateInputValidator dateInputValidator = new DateInputValidator();
        OrderInputValidator orderInputValidator = new OrderInputValidator();
        EventController eventController = new EventController(dateInputValidator, orderInputValidator);
        eventController.event();
    }
}
