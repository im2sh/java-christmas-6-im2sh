package christmas.request;

import java.util.Map;

public class FoodOrderRequest {
    private final Map<String, Integer> order;
    private final int amount;

    public FoodOrderRequest(Map<String, Integer> order, int amount) {
        this.order = order;
        this.amount = amount;
    }

    public Map<String, Integer> getOrder() {
        return order;
    }

    public int getAmount() {
        return amount;
    }
}
