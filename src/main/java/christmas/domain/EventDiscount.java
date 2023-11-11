package christmas.domain;

public enum EventDiscount {
    BASIC(1000),
    FIXED_MONEY(2023);
    private final int discountMoney;

    EventDiscount(int discountMoney) {
        this.discountMoney = discountMoney;
    }

    public int getDiscountMoney() {
        return discountMoney;
    }
}
