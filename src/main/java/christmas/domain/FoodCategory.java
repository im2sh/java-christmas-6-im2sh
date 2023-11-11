package christmas.domain;

import christmas.domain.Food;
import java.util.Arrays;
import java.util.List;

public enum FoodCategory {
    에피타이저(Arrays.asList(
            new Food("양송이수프", 6000),
            new Food("타파스", 5500),
            new Food("시저샐러드", 8000)
    )),
    메인(Arrays.asList(
            new Food("티본스테이크", 55000),
            new Food("바비큐립", 54000),
            new Food("해산물파스타", 35000),
            new Food("크리스마스파스타", 25000)
    )),
    디저트(Arrays.asList(
            new Food("초코케이크", 15000),
            new Food("아이스크림", 5000)
    )),
    음료(Arrays.asList(
            new Food("제로콜라", 3000),
            new Food("레드와인", 6000),
            new Food("샴페인", 25000)
    ));

    private List<Food> foods;

    FoodCategory(List<Food> foods) {
        this.foods = foods;
    }

    public List<Food> getFoods() {
        return foods;
    }
}
