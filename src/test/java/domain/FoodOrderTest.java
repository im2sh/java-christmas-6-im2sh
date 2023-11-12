package domain;

import christmas.domain.FoodOrder;
import christmas.request.FoodOrderRequest;
import christmas.validator.OrderInputValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FoodOrderTest {

    @Test
    @DisplayName("평일 이벤트 할인 금액 테스트")
    public void 평일_이벤트_할인_테스트() {
        //given
        OrderInputValidator orderInputValidator = new OrderInputValidator();

        String order1 = "해산물파스타-2,초코케이크-2,제로콜라-1";
        FoodOrderRequest foodOrderRequest1 = orderInputValidator.parseOrders(order1);

        String order2 = "해산물파스타-2,제로콜라-1";
        FoodOrderRequest foodOrderRequest2 = orderInputValidator.parseOrders(order2);
        //when
        FoodOrder foodOrder1 = new FoodOrder(foodOrderRequest1);
        int dessertCount1 = foodOrder1.checkWeekEvent();

        FoodOrder foodOrder2 = new FoodOrder(foodOrderRequest2);
        int dessertCount2 = foodOrder2.checkWeekEvent();

        //then
        Assertions.assertEquals(1, dessertCount1);
        Assertions.assertEquals(0,dessertCount2);
    }

}
