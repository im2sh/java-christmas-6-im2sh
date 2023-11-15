package christmas.validator;

import static christmas.validator.constants.ErrorMessage.ORDER_INPUT_ERROR;

import christmas.domain.constants.FoodCategory;
import christmas.request.FoodOrderRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderInputValidator {
    private static final String COMMA = ",";
    private static final String DASH = "-";
    private static final int ZERO = 0;
    private static final int PARSING_STANDARD = 2;

    public OrderInputValidator() {
    }

    public FoodOrderRequest parseOrders(String order) {
        Map<String, Integer> parsedOrder = new HashMap<>();
        NotNullValidate(order);

        Arrays.stream(order.split(COMMA))
                .forEach(menu -> divideOrders(menu, parsedOrder));

        validateBeverageOnly(parsedOrder);
        validateQuantityExceeded(parsedOrder);
        int amount = calculateAmount(parsedOrder);
        FoodOrderRequest foodOrderRequest = new FoodOrderRequest(parsedOrder, amount);
        return foodOrderRequest;
    }

    private int calculateAmount(Map<String, Integer> parseOrders) {
        AtomicInteger total = new AtomicInteger();
        parseOrders.forEach((foodName, quantity) ->
                Arrays.stream(FoodCategory.values())
                        .flatMap(category -> category.getFoods().stream())
                        .filter(food -> food.getName().equals(foodName))
                        .findFirst()
                        .ifPresent(food -> total.addAndGet(food.getPrice() * quantity))
        );
        return total.get();
    }

    private void divideOrders(String menu, Map<String, Integer> parsedOrder) {
        String[] foods = validateMenuFormat(menu.split(DASH));
        String foodName = validateFoodName(foods[0]);
        validateIsExistsAlreadyFood(parsedOrder, foodName);
        parsedOrder.put(foodName, parseQuantity(foods[1]));
    }

    private String[] validateMenuFormat(String[] foods) {
        if (foods.length != PARSING_STANDARD) {
            throw new IllegalArgumentException(ORDER_INPUT_ERROR.getMessage());
        }
        return foods;
    }

    private String validateFoodName(String foodName) {
        validateSpace(foodName);
        validateMenuExistence(foodName);
        return foodName;
    }

    private void validateMenuExistence(String foodName) {
        if (!Arrays.stream(FoodCategory.values())
                .flatMap(category -> category.getFoods().stream())
                .anyMatch(food -> foodName.equals(food.getName()))) {
            throw new IllegalArgumentException(ORDER_INPUT_ERROR.getMessage());
        }
    }

    private void validateSpace(String foodName) {
        if (foodName.contains(" ")) {
            throw new IllegalArgumentException(ORDER_INPUT_ERROR.getMessage());
        }
    }

    private void validateIsExistsAlreadyFood(Map<String, Integer> parsedOrder, String foodName) {
        if (parsedOrder.containsKey(foodName)) {
            throw new IllegalArgumentException(ORDER_INPUT_ERROR.getMessage());
        }
    }

    private int parseQuantity(String quantityString) {
        try {
            int quantity = Integer.parseInt(quantityString);
            validateQuantityMinimum(quantity);
            return quantity;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ORDER_INPUT_ERROR.getMessage());
        }
    }

    private void validateQuantityMinimum(int quantity) {
        if (quantity <= ZERO) {
            throw new IllegalArgumentException(ORDER_INPUT_ERROR.getMessage());
        }
    }

    private void validateBeverageOnly(Map<String, Integer> parsedOrder) {
        if (parsedOrder.keySet().stream().allMatch(this::isBeverage)) {
            throw new IllegalArgumentException(ORDER_INPUT_ERROR.getMessage());
        }
    }

    private boolean isBeverage(String foodName) {
        return Arrays.asList("제로콜라", "레드와인", "샴페인").contains(foodName);
    }

    private void validateQuantityExceeded(Map<String, Integer> parsedOrder) {
        if (parsedOrder.values().stream().mapToInt(Integer::intValue).sum() > 20) {
            throw new IllegalArgumentException(ORDER_INPUT_ERROR.getMessage());
        }
    }

    private void NotNullValidate(String order) {
        if (order == null) {
            throw new IllegalArgumentException(ORDER_INPUT_ERROR.getMessage());
        }
    }
}
