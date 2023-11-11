//package service;
//
//import christmas.domain.FoodOrder;
//import christmas.response.OrderHistoryResponse;
//import christmas.service.OrderService;
//import java.util.HashMap;
//import java.util.Map;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//public class OrderServiceTest {
//
//    @Test
//    @DisplayName("주문 내역을 출력한다.")
//    public void 주문_내역_출력() throws Exception {
//        //given
//        Map<String,Integer> order = new HashMap<>();
//        order.put("해산물파스타",2);
//        order.put("초코케이크",1);
//        order.put("제로콜라",1);
//
//        //when
//        FoodOrder foodOrder = new FoodOrder();
//        OrderService orderService = new OrderService(foodOrder);
//        OrderHistoryResponse orderHistoryResponse = orderService.getOrderHistoryResponse();
//
//        //then
//        Assertions.assertEquals(foodOrder.getOrder(),orderHistoryResponse.getOrder());
//    }
//
//}
