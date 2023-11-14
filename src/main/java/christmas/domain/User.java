package christmas.domain;

import static christmas.domain.Badge.NONE;
import static christmas.domain.Badge.SANTA;
import static christmas.domain.Badge.STAR;
import static christmas.domain.Badge.TREE;

import java.util.Arrays;

public class User {
    private static int CHRISTMAS = 25;
    private static int LAST_DAY = 31;
    private static int UNIT_HUNDRED = 100;
    private final int reservationDate;
    private Badge badge;

    public User(int reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Badge getBadge() {
        return badge;
    }

    public boolean checkChristmasDate() {
        if (reservationDate <= CHRISTMAS) {
            return true;
        }
        return false;
    }

    public int checkChristmasEvent(int discount) {
        return discount + (reservationDate * UNIT_HUNDRED - UNIT_HUNDRED);
    }

    public boolean checkWeekDate() {
        int[] weekDates = {3, 10, 17, 24};
        if (reservationDate == LAST_DAY) {
            return true;
        }

        for (int startDate : weekDates) {
            if (reservationDate >= startDate && reservationDate <= startDate + 4) {
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

    public boolean checkSpecialDate() {
        Integer[] specialDates = {3, 10, 17, 24, 25, 31};
        return Arrays.asList(specialDates).contains(reservationDate);
    }

    public void receiveBadge(int discountAmount) {
        if (discountAmount >= SANTA.getRequirement()) {
            this.badge = SANTA;
        }
        if (discountAmount >= TREE.getRequirement() && discountAmount < SANTA.getRequirement()) {
            this.badge = TREE;
        }
        if (discountAmount >= STAR.getRequirement() && discountAmount < TREE.getRequirement()) {
            this.badge = STAR;
        }
        if (discountAmount < STAR.getRequirement()) {
            this.badge = NONE;
        }
    }
}
