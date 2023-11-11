package service;

import christmas.domain.FoodOrder;
import christmas.request.FoodOrderRequest;
import christmas.response.OrderHistoryResponse;
import christmas.service.OrderService;
import christmas.validator.OrderInputValidator;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    @Test
    @DisplayName("주문 내역을 출력한다.")
    public void 주문_내역_출력() throws Exception {
        //given
        String order = "해산물파스타-2,초코케이크-1,제로콜라-1";
        OrderInputValidator orderInputValidator = new OrderInputValidator();
        FoodOrderRequest foodOrderRequest = orderInputValidator.parseOrders(order);

        //when
        FoodOrder foodOrder = new FoodOrder(foodOrderRequest);
        OrderService orderService = new OrderService(foodOrder);
        OrderHistoryResponse orderHistoryResponse = orderService.getOrderHistoryResponse();

        //then
        Assertions.assertEquals(foodOrder.getOrder(),orderHistoryResponse.getOrder());
    }

}
