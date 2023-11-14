package christmas.service;

import christmas.domain.Event;
import christmas.domain.User;

public class DiscountService {
    private final User user;
    private final Event event;

    public DiscountService(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    public int calculateBenefitAmount() {
        return event.calculateBenefitAmount();
    }
}
