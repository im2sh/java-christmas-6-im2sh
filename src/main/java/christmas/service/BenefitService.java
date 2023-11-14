package christmas.service;

import christmas.domain.Event;
import christmas.domain.FoodOrder;

public class BenefitService {
    private final FoodOrder foodOrder;
    private final Event event;

    public BenefitService(FoodOrder foodOrder, Event event) {
        this.foodOrder = foodOrder;
        this.event = event;
    }

    public int calculateBenefitAmount() {
        return event.calculateBenefitAmount();
    }

    public int calculateFinalPaymentAmount() {
        return foodOrder.calculateExpectedPaymentAmount(calculateBenefitAmount());
    }
}
