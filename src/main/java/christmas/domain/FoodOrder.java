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
}
