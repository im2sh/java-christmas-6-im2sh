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

    public int checkWeekEvent() {
        return (int) order.keySet().stream()
                .filter(foodName -> FoodCategory.디저트.getFoods().stream()
                        .anyMatch(food -> foodName.equals(food.getName())))
                .count();
    }
}
