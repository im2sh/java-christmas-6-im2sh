package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.Event;
import christmas.domain.EventDetail;
import christmas.domain.constants.EventDiscount;
import christmas.domain.constants.EventName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    @DisplayName("적용중인 이벤트의 총 혜택 금액을 반환한다.")
    public void 총_혜택_금액_테스트() throws Exception {
        //given
        Map<String, Integer> christmas = new HashMap<>();
        Map<String, Integer> weekend = new HashMap<>();
        Map<String, Integer> week = new HashMap<>();

        christmas.put(EventName.CHRISTMAS.getEventName(), EventDiscount.BASIC.getDiscountMoney());
        weekend.put(EventName.WEEKEND.getEventName(), EventDiscount.FIXED_MONEY.getDiscountMoney());
        week.put(EventName.WEEK.getEventName(), 0);

        EventDetail christmasDetail = new EventDetail(christmas);
        EventDetail weekendDetail = new EventDetail(weekend);
        EventDetail weekDetail = new EventDetail(week);

        List<EventDetail> validEventDetail = new ArrayList<>();
        validEventDetail.add(christmasDetail);
        validEventDetail.add(weekendDetail);

        List<EventDetail> nonValidEventDetail = new ArrayList<>();
        nonValidEventDetail.add(weekDetail);

        Event validDiscountEvent = new Event(validEventDetail);
        Event nonValidDiscountEvent = new Event(nonValidEventDetail);

        //when
        int validDiscountAmount = validDiscountEvent.calculateBenefitAmount();
        int nonValidDiscountAmount = nonValidDiscountEvent.calculateBenefitAmount();

        //then
        assertEquals(3023, validDiscountAmount);
        assertEquals(0, nonValidDiscountAmount);
    }

}
