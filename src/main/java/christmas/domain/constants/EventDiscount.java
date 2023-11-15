package christmas.domain.constants;

public enum EventDiscount {
    BASIC(1000),
    FIXED_MONEY(2023),
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