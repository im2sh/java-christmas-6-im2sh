package christmas.service;

import christmas.domain.Event;
import christmas.domain.EventDetail;
import christmas.domain.EventDiscount;
import christmas.domain.EventName;
import christmas.domain.FoodOrder;
import christmas.domain.User;
import christmas.response.EventResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventService {
    private static final int ZERO = 0;
    private static final int GIFT = 25000;
    private final User user;
    private final FoodOrder foodOrder;

    public EventService(User user, FoodOrder foodOrder) {
        this.user = user;
        this.foodOrder = foodOrder;
    }

    public EventResponse eventToResponse(Event event){
        return event.toResponse();
    }

    public boolean isExistsGift(){
        return foodOrder.checkGiftEvent();
    }

    public Event occurEvent() {
        List<EventDetail> allEvent = new ArrayList<>();
        if(!foodOrder.checkMinimumEventRequirement()){
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
        String eventName = EventName.CHRISTMAS.getEventName();
        int discount = EventDiscount.BASIC.getDiscountMoney();
        int eventDiscount = user.checkChristmasEvent(discount);

        if (!user.checkChristmasDate()) {
            christmasEvent.put(EventName.NOTING.getEventName(), ZERO);
            return new EventDetail(christmasEvent);
        }

        christmasEvent.put(eventName, eventDiscount);
        return new EventDetail(christmasEvent);
    }

    private EventDetail weekEvent() {
        Map<String, Integer> weekEvent = new HashMap<>();

        String eventName = EventName.WEEK.getEventName();
        int discount = EventDiscount.FIXED_MONEY.getDiscountMoney();
        int desertCount = foodOrder.checkWeekEventDiscount();

        if (desertCount == ZERO || !user.checkWeekDate()) {
            weekEvent.put(EventName.NOTING.getEventName(), ZERO);
            return new EventDetail(weekEvent);
        }
        weekEvent.put(eventName, discount * desertCount);
        return new EventDetail(weekEvent);
    }

    private EventDetail weekendEvent() {
        Map<String, Integer> weekendEvent = new HashMap<>();
        String eventName = EventName.WEEKEND.getEventName();
        int discount = EventDiscount.FIXED_MONEY.getDiscountMoney();
        int mainCount = foodOrder.checkWeekendDiscount();

        if (mainCount == ZERO || !user.checkWeekendDate()) {
            weekendEvent.put(EventName.NOTING.getEventName(), ZERO);
            return new EventDetail(weekendEvent);
        }
        weekendEvent.put(eventName, discount * mainCount);
        return new EventDetail(weekendEvent);
    }

    private EventDetail specialEvent() {
        Map<String, Integer> specialEvent = new HashMap<>();
        String eventName = EventName.SPECIAL.getEventName();
        int discount = EventDiscount.BASIC.getDiscountMoney();

        if (!user.checkSpecialDate()) {
            specialEvent.put(EventName.NOTING.getEventName(), ZERO);
            return new EventDetail(specialEvent);
        }
        specialEvent.put(eventName, discount);
        return new EventDetail(specialEvent);
    }

    private EventDetail giftEvent() {
        Map<String, Integer> giftEvent = new HashMap<>();
        if (!foodOrder.checkGiftEvent()) {
            giftEvent.put(EventName.NOTING.getEventName(), ZERO);
            return new EventDetail(giftEvent);
        }
        giftEvent.put(EventName.GIFT.getEventName(), GIFT);
        return new EventDetail(giftEvent);
    }
}
