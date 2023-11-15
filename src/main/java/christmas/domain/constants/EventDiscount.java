package christmas.domain.constants;

public enum EventDiscount {
    CHRISTMAS_OR_SPECIAL(1000),
    WEEK_OR_WEEKEND(2023),
    ZERO(0),
    GIFT_MONEY(25000);
    private final int discountMoney;

    EventDiscount(int discountMoney) {
        this.discountMoney = discountMoney;
    }

    public int getDiscountMoney() {
        return discountMoney;
    }
}
