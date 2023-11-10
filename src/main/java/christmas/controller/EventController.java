package christmas.controller;

import christmas.domain.User;
import christmas.validator.DateInputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {
    private final DateInputValidator dateInputValidator;

    public EventController(DateInputValidator dateInputValidator){
        this.dateInputValidator = dateInputValidator;
    }
    public void event() {
        User user = new User(inputReservationDate());
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

}
