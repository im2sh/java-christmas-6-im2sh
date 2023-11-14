package christmas.controller;

import christmas.domain.Event;
import christmas.domain.FoodOrder;
import christmas.service.BenefitService;
import christmas.view.OutputView;

public class BenefitController {
    private final BenefitService benefitService;

    public BenefitController(FoodOrder foodOrder, Event event) {
        this.benefitService = new BenefitService(foodOrder, event);
    }

    public void printBenefitAmount() {
        OutputView.printDiscountAmount(benefitService.calculateBenefitAmount());
    }

    public void printFinalPaymentAmount() {
        OutputView.printExpectedPaymentAmount(benefitService.calculateFinalPaymentAmount());
    }
}
