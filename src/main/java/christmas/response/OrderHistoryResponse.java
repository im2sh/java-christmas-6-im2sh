package christmas.response;

import java.util.Map;

public class OrderHistoryResponse {
    private Map<String,Integer> order;

    public OrderHistoryResponse(Map<String, Integer> order) {
        this.order = order;
    }

    public Map<String, Integer> getOrder() {
        return order;
    }
}
