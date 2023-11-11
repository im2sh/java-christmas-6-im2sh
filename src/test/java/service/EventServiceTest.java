package service;

import christmas.domain.FoodOrder;
import christmas.domain.User;
import christmas.request.FoodOrderRequest;
import christmas.service.EventService;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventServiceTest {
    @Test
    @DisplayName("할인 전 총 주믄금액이 12만원을 넘으면 샴페인이 지급된다.")
    public void 증정_이벤트_테스트() throws Exception {
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

        EventService canEventService = new EventService(user, canGiftOrder);
        boolean canGift = canEventService.giftEvent();

        EventService cannotEventService = new EventService(user, cannotGiftOrder);
        boolean cannotGift = cannotEventService.giftEvent();

        //then
        Assertions.assertEquals(true, canGift);
        Assertions.assertEquals(false, cannotGift);
    }

}
