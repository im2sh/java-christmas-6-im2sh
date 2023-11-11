package christmas.service;

import christmas.domain.FoodOrder;
import christmas.domain.User;

public class EventService {
    private final User user;
    private final FoodOrder foodOrder;

    public EventService(User user, FoodOrder foodOrder) {
        this.user = user;
        this.foodOrder = foodOrder;
    }

    public boolean giftEvent(){
        if(foodOrder.getAmount() >= 120000)
            return true;
        return false;
    }

}
