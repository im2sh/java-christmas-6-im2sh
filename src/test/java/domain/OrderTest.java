package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderTest {

    @ParameterizedTest
    @ValueSource(strings = {"해산물-2,제로콜라-1", "티본-1,샴페인-1", "해산물파스타-1,바비큐립-1,샴페-2", "초코-1,레드와인-1,크리스마스-2,아이스크림-1"})
    @DisplayName("입력받은 메뉴가 존재하지 않으면 예외가 발생한다.")
    public void 입력_메뉴_존재_테스트(String input) throws Exception {
        Assertions.assertThatThrownBy(() -> new FoodOrder(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-0,제로콜라-1", "티본스테이크-3,샴페인-0", "크리스마스파스타-2,제로콜라-0"})
    @DisplayName("입력한 메뉴의 개수가 0개인 경우 예외가 발생한다.")
    public void 메뉴_0개_테스트(String input) throws Exception {
        Assertions.assertThatThrownBy(() -> new FoodOrder(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"양송이수프,시저샐러드-1", "해산물파스타 -0,제로콜라-1", "티본스테이크3,샴페인-0", "크리스마스파스타-2제로콜라-0", "타파스- 1,초코케이크-1"})
    @DisplayName("입력한 메뉴의 형식이 다를 경우 예외가 발생한다.")
    public void 입력_형식_테스트(String input) throws Exception {
        Assertions.assertThatThrownBy(() -> new FoodOrder(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-1", "샴페인-2,제로콜라-1", "레드와인-2,제로콜라-1,샴페인-1", "샴페인-1"})
    @DisplayName("음료수만 구매한 경우 경우 예외가 발생한다.")
    public void 음료수_오직_구매_테스트(String input) throws Exception {
        Assertions.assertThatThrownBy(() -> new FoodOrder(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"시저샐러드-1,시저샐러드-1", "해산물파스타-2,제로콜라-1,해산물파스타-1", "초코케이크-1,레드와인-1,초코케이크-1"})
    @DisplayName("입력한 메뉴가 중복인 경우 예외가 발생한다.")
    public void 입력_중복_테스트(String input) throws Exception {
        Assertions.assertThatThrownBy(() -> new FoodOrder(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-21", "티본스테이크-15,제로콜라-15", "초코케이크-11,아이스크림-10"})
    @DisplayName("입력한 음식의 수량이 20개가 넘을 경우 경우 예외가 발생한다.")
    public void 음식_수량_테스트(String input) throws Exception {
        Assertions.assertThatThrownBy(() -> new FoodOrder(input)).isInstanceOf(IllegalArgumentException.class);
    }
}
