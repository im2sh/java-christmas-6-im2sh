package christmas.domain;

import static christmas.domain.EventDiscount.*;

import christmas.request.FoodOrderRequest;
import java.util.Map;

public class FoodOrder {
    private final Map<String, Integer> order;
    private final int amount;
    private static final int GIFT_STANDARD = 120000;


    public FoodOrder(FoodOrderRequest foodOrderRequest) {
        this.order = foodOrderRequest.getOrder();
        this.amount = foodOrderRequest.getAmount();
    }

    public Map<String, Integer> getOrder() {
        return order;
    }

    public int getAmount() {
        return amount;
    }

    public boolean checkMinimumEventRequirement() {
        if (amount >= 10000) {
            return true;
        }
        return false;
    }

    public boolean checkGiftEvent() {
        if (amount >= GIFT_STANDARD) {
            return true;
        }
        return false;
    }

    public int checkWeekEventDiscount() {
        return order.entrySet().stream()
                .filter(entry -> FoodCategory.디저트.getFoods().stream()
                        .anyMatch(food -> entry.getKey().equals(food.getName())))
                .mapToInt(entry -> entry.getValue())
                .sum();
    }

    public int checkWeekendDiscount() {
        return order.entrySet().stream()
                .filter(entry -> FoodCategory.메인.getFoods().stream()
                        .anyMatch(food -> entry.getKey().equals(food.getName())))
                .mapToInt(entry -> entry.getValue())
                .sum();
    }

    public int calculateExpectedPaymentAmount(int calculateBenefitAmount) {
        if(checkGiftEvent())
            return amount - (calculateBenefitAmount - GIFT_MONEY.getDiscountMoney());
        return amount - calculateBenefitAmount;
    }

}
