package christmas.validator;

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
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private void validateNotNull(String reservationDate) {
        if (reservationDate == null) {
            throw new IllegalArgumentException("[ERROR] 입력 값이 null 입니다. 다시 입력해 주세요.");
        }
    }

    private void validateRange(int reservationDate) {
        if (reservationDate < START_DATE || reservationDate > END_DATE) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않는 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private int convertToInteger(String reservationDate) {
        return Integer.parseInt(reservationDate);
    }
}
