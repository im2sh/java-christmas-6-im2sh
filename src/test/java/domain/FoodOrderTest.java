package domain;

import christmas.domain.EventDiscount;
import christmas.domain.FoodOrder;
import christmas.domain.User;
import christmas.request.FoodOrderRequest;
import christmas.validator.OrderInputValidator;
import java.util.HashMap;
import java.util.Map;
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
        int dessertCount1 = foodOrder1.checkWeekEventDiscount();

        FoodOrder foodOrder2 = new FoodOrder(foodOrderRequest2);
        int dessertCount2 = foodOrder2.checkWeekEventDiscount();

        //then
        Assertions.assertEquals(2 * 2023, dessertCount1 * EventDiscount.FIXED_MONEY.getDiscountMoney());
        Assertions.assertEquals(0 * 2323, dessertCount2 * EventDiscount.FIXED_MONEY.getDiscountMoney());
    }

    @Test
    @DisplayName("주말 이벤트 할인 금액 테스트")
    public void 주말_이벤트_할인_테스트() {
        //given
        OrderInputValidator orderInputValidator = new OrderInputValidator();

        String order1 = "티본스테이크-2,바비큐립-1,제로콜라-1";
        FoodOrderRequest foodOrderRequest1 = orderInputValidator.parseOrders(order1);

        String order2 = "타파스-1,시저샐러드-1,초코케이크-1,제로콜라-1";
        FoodOrderRequest foodOrderRequest2 = orderInputValidator.parseOrders(order2);
        //when
        FoodOrder foodOrder1 = new FoodOrder(foodOrderRequest1);
        int mainCount1 = foodOrder1.checkWeekendDiscount();

        FoodOrder foodOrder2 = new FoodOrder(foodOrderRequest2);
        int mainCount2 = foodOrder2.checkWeekendDiscount();

        //then
        Assertions.assertEquals(3 * 2023, mainCount1 * EventDiscount.FIXED_MONEY.getDiscountMoney());
        Assertions.assertEquals(0 * 2323, mainCount2 * EventDiscount.FIXED_MONEY.getDiscountMoney());
    }

    @Test
    @DisplayName("할인 전 총 주문 금액이 12만원을 넘으면 true를 반환한다.")
    public void 선물_증정_이벤트_최소_금액_테스트() throws Exception {
        //given
        User user = new User(25);
        Map<String, Integer> order1 = new HashMap<>();
        order1.put("티본스테이크", 3);
        FoodOrderRequest foodOrderRequest1 = new FoodOrderRequest(order1, 165000);

        Map<String, Integer> order2 = new HashMap<>();
        order2.put("해산물파스타", 1);
        FoodOrderRequest foodOrderRequest2 = new FoodOrderRequest(order2, 35000);

        //when
        FoodOrder canGiftOrder = new FoodOrder(foodOrderRequest1);
        FoodOrder cannotGiftOrder = new FoodOrder(foodOrderRequest2);

        //then
        Assertions.assertEquals(true, canGiftOrder.checkGiftEvent());
        Assertions.assertEquals(false, cannotGiftOrder.checkGiftEvent());
    }

    @Test
    @DisplayName("예상 결제 금액을 출력한다.")
    public void 예상_결졔_금액_테스트() throws Exception {
        OrderInputValidator orderInputValidator = new OrderInputValidator();
        //given
        String order = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        FoodOrderRequest foodOrderRequest = orderInputValidator.parseOrders(order);
        //when
        FoodOrder foodOrder = new FoodOrder(foodOrderRequest);
        int paymentAmount = foodOrder.calculateExpectedPaymentAmount(31246);

        //then
        Assertions.assertEquals(135754, paymentAmount);
    }

}
