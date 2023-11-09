package domain;

public class User {
    private final int reservationDate;

    public User(String reservationDate) {
        int validatedDate = Validate(reservationDate);
        this.reservationDate = validatedDate;
    }

    private int Validate(String reservationDate) {
        NotNullValidate(reservationDate);
        NotValueValidate(reservationDate);
        int date = convertToInteger(reservationDate);
        RangeValidate(date);
        return date;
    }

    private void NotValueValidate(String reservationDate) {
        if (!reservationDate.matches("\\d+")) {
            throw new IllegalArgumentException("[ERROR] 입력 날짜는 숫자로만 입력되어야 합니다. 다시 입력해 주세요.");
        }
    }

    private void NotNullValidate(String reservationDate) {
        if (reservationDate == null) {
            throw new IllegalArgumentException("[ERROR] 입력 값이 null 입니다. 다시 입력해 주세요.");
        }
    }

    private void RangeValidate(int reservationDate) {
        final int MIN_DATE = 1;
        final int MAX_DATE = 31;
        if (reservationDate < MIN_DATE || reservationDate > MAX_DATE) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않는 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private int convertToInteger(String reservationDate) {
        return Integer.parseInt(reservationDate);
    }
}
