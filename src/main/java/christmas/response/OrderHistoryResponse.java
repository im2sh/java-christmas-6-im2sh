package christmas.response;

import java.util.Map;

public class OrderHistoryResponse {
    private final Map<String, Integer> order;
    private final int amount;

    public OrderHistoryResponse(Map<String, Integer> order, int amount) {
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
