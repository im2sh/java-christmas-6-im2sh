package service;

import static christmas.domain.constants.EventDiscount.BASIC;
import static christmas.domain.constants.EventDiscount.GIFT_MONEY;
import static christmas.domain.constants.EventName.CHRISTMAS;
import static christmas.domain.constants.EventName.GIFT;
import static christmas.domain.constants.EventName.SPECIAL;
import static christmas.domain.constants.EventName.WEEK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.constants.Badge;
import christmas.domain.Event;
import christmas.domain.EventDetail;
import christmas.domain.FoodOrder;
import christmas.domain.User;
import christmas.request.FoodOrderRequest;
import christmas.service.BenefitService;
import christmas.validator.OrderInputValidator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BenefitServiceTest {

    private BenefitService benefitService;

    @BeforeEach
    void setUp() {
        User user = new User(25);
        OrderInputValidator orderInputValidator = new OrderInputValidator();
        String order1 = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        FoodOrderRequest foodOrderRequest = orderInputValidator.parseOrders(order1);
        FoodOrder foodOrder = new FoodOrder(foodOrderRequest);

        Map<String, Integer> christmas = new HashMap<>();
        christmas.put(CHRISTMAS.getEventName(), 3400);
        EventDetail christmasDetail = new EventDetail(christmas);

        Map<String, Integer> week = new HashMap<>();
        week.put(WEEK.getEventName(), 4046);
        EventDetail weekDetail = new EventDetail(week);

        Map<String, Integer> special = new HashMap<>();
        special.put(SPECIAL.getEventName(), BASIC.getDiscountMoney());
        EventDetail specialDetail = new EventDetail(special);

        Map<String, Integer> gift = new HashMap<>();
        gift.put(GIFT.getEventName(), GIFT_MONEY.getDiscountMoney());
        EventDetail giftDetail = new EventDetail(gift);

        List<EventDetail> eventDetail = new ArrayList<>();

        eventDetail.add(christmasDetail);
        eventDetail.add(weekDetail);
        eventDetail.add(specialDetail);
        eventDetail.add(giftDetail);

        Event event = new Event(eventDetail);
        benefitService = new BenefitService(user, foodOrder, event);
    }

    @Test
    @DisplayName("할인 금액을 반환한다.")
    public void 할인_금액_계산_테스트() throws Exception {
        //when
        int benefitAmount = benefitService.calculateBenefitAmount();
        //then
        assertEquals(33446, benefitAmount);
    }

    @Test
    @DisplayName("최종 결제 금액을 반환한다.")
    public void 최종_결제_금액_테스트() throws Exception {
        //when
        int paymentAmount = benefitService.calculateFinalPaymentAmount();
        //then
        assertEquals(133554, paymentAmount);

    }


    @Test
    @DisplayName("뱃지를 부여하고 반환한다.")
    public void 뱃지_발급_테스트() throws Exception {
        //when
        benefitService.evaluateUserBadge();
        Badge userBadge = benefitService.getUserBadge();

        //then
        assertEquals(Badge.SANTA, userBadge);
    }

}
