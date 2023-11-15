package christmas.validator;

import static christmas.validator.constants.ErrorMessage.*;

import christmas.validator.constants.ErrorMessage;

public class DateInputValidator {
    private static final int START_DATE = 1;
    private static final int END_DATE = 31;

    public DateInputValidator() {
    }

    public int validateInputDate(String reservationDate) {
        validateNotNull(reservationDate);
        validateNotValue(reservationDate);
        int date = convertToInteger(reservationDate);
        validateRange(date);
        return date;
    }

    private void validateNotValue(String reservationDate) {
        if (!reservationDate.matches("\\d+")) {
            throw new IllegalArgumentException(DATE_INPUT_ERROR.getMessage());
        }
    }

    private void validateNotNull(String reservationDate) {
        if (reservationDate == null) {
            throw new IllegalArgumentException(DATE_INPUT_ERROR.getMessage());
        }
    }

    private void validateRange(int reservationDate) {
        if (reservationDate < START_DATE || reservationDate > END_DATE) {
            throw new IllegalArgumentException(DATE_INPUT_ERROR.getMessage());
        }
    }

    private int convertToInteger(String reservationDate) {
        return Integer.parseInt(reservationDate);
    }
}
