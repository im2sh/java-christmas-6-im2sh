package christmas.domain;

public class User {
    private final int reservationDate;

    public User(int reservationDate) {
        this.reservationDate = reservationDate;
    }

    public boolean checkChristmasDate() {
        if (reservationDate <= 25) {
            return true;
        }
        return false;
    }

    public int checkChristmasEvent(int discount) {
        return discount + (reservationDate * 100 - 100);
    }

    public boolean checkWeekDate() {
        int[] weekDates = {4, 11, 18, 25};

        for (int startDate : weekDates) {
            if (reservationDate >= startDate && reservationDate <= startDate + 3) {
                return true;
            }
        }
        return false;
    }

    public boolean checkWeekendDate() {
        int[] weekendDates = {1, 8, 15, 22, 29};

        for (int startDate : weekendDates) {
            if (reservationDate >= startDate && reservationDate <= startDate + 1) {
                return true;
            }
        }
        return false;
    }
}
