package christmas.service;

import christmas.domain.Event;
import christmas.domain.EventDetail;
import christmas.domain.EventDiscount;
import christmas.domain.EventName;
import christmas.domain.FoodOrder;
import christmas.domain.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventService {
    private final User user;
    private final FoodOrder foodOrder;
    private Event event;

    public EventService(User user, FoodOrder foodOrder) {
        this.user = user;
        this.foodOrder = foodOrder;
    }

    public boolean giftEvent() {
        if (foodOrder.getAmount() >= 120000) {
            return true;
        }
        return false;
    }

    public Event showBenefits() {
        discountEvent();
        return event;
    }

    public void discountEvent() {
        List<EventDetail> allEvent = new ArrayList<>();
        allEvent.add(christmasEvent());
        allEvent.add(weekEvent());
        allEvent.add(weekendEvent());
        allEvent.add(specialEvent());
        event = new Event(allEvent);
    }

    private EventDetail christmasEvent() {
        Map<String, Integer> christmasEvent = new HashMap<>();
        String eventName = EventName.CHRISTMAS.getEventName();
        int discount = EventDiscount.BASIC.getDiscountMoney();
        int eventDiscount = user.checkChristmasEvent(discount);

        if (!user.checkChristmasDate()) {
            christmasEvent.put(EventName.NOTING.getEventName(), 0);
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

        if (desertCount == 0 || !user.checkWeekDate()) {
            weekEvent.put(EventName.NOTING.getEventName(), 0);
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

        if (mainCount == 0 || !user.checkWeekendDate()) {
            weekendEvent.put(EventName.NOTING.getEventName(), 0);
        }
        weekendEvent.put(eventName, discount * mainCount);
        return new EventDetail(weekendEvent);
    }

    private EventDetail specialEvent(){
        Map<String, Integer> specialEvent = new HashMap<>();
        String eventName = EventName.SPECIAL.getEventName();
        int discount = EventDiscount.BASIC.getDiscountMoney();

        if (!user.checkSpecialDate()) {
            specialEvent.put(EventName.NOTING.getEventName(), 0);
        }
        specialEvent.put(eventName, discount);
        return new EventDetail(specialEvent);
    }
}
