package christmas.service;

import static christmas.domain.constants.EventDiscount.CHRISTMAS_OR_SPECIAL;
import static christmas.domain.constants.EventDiscount.WEEK_OR_WEEKEND;
import static christmas.domain.constants.EventDiscount.GIFT_MONEY;
import static christmas.domain.constants.EventDiscount.ZERO;
import static christmas.domain.constants.EventName.CHRISTMAS;
import static christmas.domain.constants.EventName.GIFT;
import static christmas.domain.constants.EventName.NOTING;
import static christmas.domain.constants.EventName.SPECIAL;
import static christmas.domain.constants.EventName.WEEK;
import static christmas.domain.constants.EventName.WEEKEND;

import christmas.domain.Event;
import christmas.domain.EventDetail;
import christmas.domain.FoodOrder;
import christmas.domain.User;
import christmas.response.EventResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventService {
    private final User user;
    private final FoodOrder foodOrder;


    public EventService(User user, FoodOrder foodOrder) {
        this.user = user;
        this.foodOrder = foodOrder;
    }

    public EventResponse eventToResponse(Event event) {
        return event.toResponse();
    }

    public boolean isExistsGift() {
        return foodOrder.checkGiftEvent();
    }

    public Event occurEvent() {
        List<EventDetail> allEvent = new ArrayList<>();
        if (!foodOrder.checkMinimumEventRequirement()) {
            return new Event(allEvent);
        }
        allEvent.add(christmasEvent());
        allEvent.add(weekEvent());
        allEvent.add(weekendEvent());
        allEvent.add(specialEvent());
        allEvent.add(giftEvent());
        return new Event(allEvent);
    }

    private EventDetail christmasEvent() {
        Map<String, Integer> christmasEvent = new HashMap<>();
        String eventName = CHRISTMAS.getEventName();
        int discount = CHRISTMAS_OR_SPECIAL.getDiscountMoney();
        int eventDiscount = user.checkChristmasEvent(discount);

        if (!user.checkChristmasDate()) {
            christmasEvent.put(NOTING.getEventName(), ZERO.getDiscountMoney());
            return new EventDetail(christmasEvent);
        }

        christmasEvent.put(eventName, eventDiscount);
        return new EventDetail(christmasEvent);
    }

    private EventDetail weekEvent() {
        Map<String, Integer> weekEvent = new HashMap<>();

        String eventName = WEEK.getEventName();
        int discount = WEEK_OR_WEEKEND.getDiscountMoney();
        int desertCount = foodOrder.checkWeekEventDiscount();

        if (desertCount == ZERO.getDiscountMoney() || !user.checkWeekDate()) {
            weekEvent.put(NOTING.getEventName(), ZERO.getDiscountMoney());
            return new EventDetail(weekEvent);
        }
        weekEvent.put(eventName, discount * desertCount);
        return new EventDetail(weekEvent);
    }

    private EventDetail weekendEvent() {
        Map<String, Integer> weekendEvent = new HashMap<>();
        String eventName = WEEKEND.getEventName();
        int discount = WEEK_OR_WEEKEND.getDiscountMoney();
        int mainCount = foodOrder.checkWeekendDiscount();

        if (mainCount == ZERO.getDiscountMoney() || !user.checkWeekendDate()) {
            weekendEvent.put(NOTING.getEventName(), ZERO.getDiscountMoney());
            return new EventDetail(weekendEvent);
        }
        weekendEvent.put(eventName, discount * mainCount);
        return new EventDetail(weekendEvent);
    }

    private EventDetail specialEvent() {
        Map<String, Integer> specialEvent = new HashMap<>();
        String eventName = SPECIAL.getEventName();
        int discount = CHRISTMAS_OR_SPECIAL.getDiscountMoney();

        if (!user.checkSpecialDate()) {
            specialEvent.put(NOTING.getEventName(), ZERO.getDiscountMoney());
            return new EventDetail(specialEvent);
        }
        specialEvent.put(eventName, discount);
        return new EventDetail(specialEvent);
    }

    private EventDetail giftEvent() {
        Map<String, Integer> giftEvent = new HashMap<>();
        if (!foodOrder.checkGiftEvent()) {
            giftEvent.put(NOTING.getEventName(), ZERO.getDiscountMoney());
            return new EventDetail(giftEvent);
        }
        giftEvent.put(GIFT.getEventName(), GIFT_MONEY.getDiscountMoney());
        return new EventDetail(giftEvent);
    }
}
