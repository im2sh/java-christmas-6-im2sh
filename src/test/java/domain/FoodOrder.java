package domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FoodOrder {
    private final Map<String, Integer> order;

    public FoodOrder(String order) {
        this.order = parseOrders(order);
    }

    public Map<String, Integer> parseOrders(String orders) {
        Map<String, Integer> parsedOrder = new HashMap<>();

        Arrays.stream(orders.split(","))
                .forEach(menu -> divideOrders(menu, parsedOrder));

        validateBeverageOnly(parsedOrder);
        validateQuantityExceeded(parsedOrder);
        return parsedOrder;
    }

    private void divideOrders(String menu, Map<String, Integer> parsedOrder) {
        String[] foods = validateMenuFormat(menu.split("-"));
        String foodName = validateFoodName(foods[0]);
        validateIsExistsAlreadyFood(parsedOrder, foodName);
        parsedOrder.put(foodName, parseQuantity(foods[1]));
    }

    private String[] validateMenuFormat(String[] foods) {
        if (foods.length != 2) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        return foods;
    }

    private String validateFoodName(String foodName) {
        validateMenuExistence(foodName);
        validateSpace(foodName);
        return foodName;
    }

    private void validateMenuExistence(String foodName) {
        if (!Arrays.stream(FoodCategory.values())
                .flatMap(category -> category.getFoods().stream())
                .anyMatch(food -> foodName.equals(food.getName()))) {
            throw new IllegalArgumentException("[ERROR] 메뉴가 존재하지 않습니다. 다시 입력해 주세요.");
        }
    }

    private void validateSpace(String foodName) {
        if (foodName.contains(" ")) {
            throw new IllegalArgumentException("[ERROR] 입력에는 공백이 허용되지 않습니다. 다시 입력해 주세요.");
        }
    }

    private void validateIsExistsAlreadyFood(Map<String, Integer> parsedOrder, String foodName) {
        if (parsedOrder.containsKey(foodName)) {
            throw new IllegalArgumentException("[ERROR] 중복된 메뉴는 입력할 수 없습니다. 다시 입력해 주세요.");
        }
    }

    private int parseQuantity(String quantityString) {
        try {
            int quantity = Integer.parseInt(quantityString);
            validateQuantityMinimum(quantity);
            return quantity;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateQuantityMinimum(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("[ERROR] 메뉴의 수량은 최소 1개 이상입니다. 다시 입력해 주세요.");
        }
    }

    private void validateBeverageOnly(Map<String, Integer> parsedOrder) {
        if (parsedOrder.keySet().stream().allMatch(this::isBeverage)) {
            throw new IllegalArgumentException("[ERROR] 음료수만 주문할 수 없습니다. 다시 입력해 주세요.");
        }
    }

    private boolean isBeverage(String foodName) {
        return Arrays.asList("제로콜라", "레드와인", "샴페인").contains(foodName);
    }

    private void validateQuantityExceeded(Map<String, Integer> parsedOrder) {
        if (parsedOrder.values().stream().mapToInt(Integer::intValue).sum() > 20) {
            throw new IllegalArgumentException("[ERROR] 주문 수량은 최대 20개입니다. 다시 입력해 주세요.");
        }
    }
}
