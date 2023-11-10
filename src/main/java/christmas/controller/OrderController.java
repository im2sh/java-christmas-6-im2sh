package christmas.controller;

import christmas.domain.FoodOrder;
import christmas.response.OrderHistoryResponse;
import christmas.service.OrderService;
import christmas.view.OutputView;

public class OrderController {
    private final OrderService orderService;

    public OrderController(FoodOrder foodOrder) {
        orderService = new OrderService(foodOrder);
    }

    public void showOrderHistory() {
        OrderHistoryResponse orderHistoryResponse = orderService.getOrderHistoryResponse();
        OutputView.printOrderList(orderHistoryResponse);
    }
}
