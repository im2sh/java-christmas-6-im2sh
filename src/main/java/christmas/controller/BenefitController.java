package christmas.controller;

import christmas.domain.Event;
import christmas.domain.User;
import christmas.service.DiscountService;
import christmas.view.OutputView;

public class BenefitController {
    private final DiscountService discountService;

    public BenefitController(User user, Event event) {
        this.discountService = new DiscountService(user, event);
    }

    public void printBenefitAmount(){
        OutputView.printDiscountAmount(discountService.calculateBenefitAmount());
    }
}
