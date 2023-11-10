package christmas.domain;

import java.util.Map;

public class FoodOrder {
    private final Map<String, Integer> order;

    public FoodOrder(Map<String, Integer> order) {
        this.order = order;
    }

    public Map<String, Integer> getOrder() {
        return order;
    }
}
