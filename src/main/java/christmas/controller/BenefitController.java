package christmas.controller;

import christmas.domain.Event;
import christmas.domain.FoodOrder;
import christmas.domain.User;
import christmas.service.BenefitService;
import christmas.view.OutputView;

public class BenefitController {
    private final BenefitService benefitService;

    public BenefitController(User user, FoodOrder foodOrder, Event event) {
        this.benefitService = new BenefitService(user, foodOrder, event);
    }

    public void printBenefitAmount() {
        OutputView.printDiscountAmount(benefitService.calculateBenefitAmount());
    }

    public void printFinalPaymentAmount() {
        OutputView.printExpectedPaymentAmount(benefitService.calculateFinalPaymentAmount());
    }

    public void evaluateUserBadge(){
        benefitService.evaluateUserBadge();
    }
    public void printUserBadge() {
        OutputView.printUserBadge(benefitService.getUserBadge());
    }
}
