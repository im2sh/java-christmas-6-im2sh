package christmas.service;

import christmas.domain.FoodOrder;
import christmas.response.OrderHistoryResponse;

public class OrderService {
    private final FoodOrder foodOrder;

    public OrderService(FoodOrder foodOrder) {
        this.foodOrder = foodOrder;
    }

    public OrderHistoryResponse getOrderHistoryResponse() {
        return new OrderHistoryResponse(foodOrder.getOrder());
    }

}
