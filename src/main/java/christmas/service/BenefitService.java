package christmas.service;

import christmas.domain.Event;
import christmas.domain.FoodOrder;

public class BenefitService {
    private static int GIFT = 25000;
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
        if (foodOrder.checkGiftEvent()) {
            return foodOrder.calculateExpectedPaymentAmount(calculateBenefitAmount() - GIFT);
        }
        return foodOrder.calculateExpectedPaymentAmount(calculateBenefitAmount());
    }
}
