package christmas.service;

import christmas.domain.Event;
import christmas.domain.FoodOrder;
import christmas.domain.User;
import christmas.domain.constants.Badge;

public class BenefitService {
    private final User user;
    private final FoodOrder foodOrder;
    private final Event event;

    public BenefitService(User user, FoodOrder foodOrder, Event event) {
        this.user = user;
        this.foodOrder = foodOrder;
        this.event = event;
    }

    public int calculateBenefitAmount() {
        return event.calculateBenefitAmount();
    }

    public int calculateFinalPaymentAmount() {
        return foodOrder.calculateExpectedPaymentAmount(calculateBenefitAmount());
    }

    public void evaluateUserBadge() {
        user.receiveBadge(calculateBenefitAmount());
    }

    public Badge getUserBadge() {
        return user.getBadge();
    }
}
