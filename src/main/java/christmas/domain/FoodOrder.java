package christmas.domain;

import christmas.request.FoodOrderRequest;
import java.util.Map;

public class FoodOrder {
    private final Map<String, Integer> order;
    private final int amount;

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
}
