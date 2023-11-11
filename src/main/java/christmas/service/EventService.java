package christmas.service;

import christmas.domain.Event;
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

    public boolean giftEvent(){
        if(foodOrder.getAmount() >= 120000)
            return true;
        return false;
    }

    public Event showBenefits(){
        discountEvent();
        return event;
    }
    public void discountEvent(){
        List<Map<String, Integer>> allEvent = new ArrayList<>();
        Map<String, Integer> christmasEvent = christmasEvent();

        allEvent.add(christmasEvent);
        event = new Event(allEvent);
    }

    private Map<String,Integer> christmasEvent(){
        Map<String,Integer> christmasEvent = new HashMap<>();
        if(!user.checkDate()) {
            christmasEvent.put(EventName.NOTING.getEventName(), 0);
            return christmasEvent;
        }
        String eventName = EventName.CHRISTMAS.getEventName();
        int discount = EventDiscount.BASIC.getDiscountMoney();
        int eventDiscount = user.checkChristmasEvent(discount);
        christmasEvent.put(eventName,eventDiscount);

        return christmasEvent;
    }

}
