package christmas;

import christmas.controller.EventController;
import christmas.validator.DateInputValidator;

public class Application {
    public static void main(String[] args) {
        DateInputValidator dateInputValidator = new DateInputValidator();
        EventController eventController = new EventController(dateInputValidator);
        eventController.event();
    }
}
