package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.EventDiscount;
import christmas.domain.User;
import christmas.validator.DateInputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class UserTest {
    private DateInputValidator dateInputValidator;

    @BeforeEach
    public void Init() {
        dateInputValidator = new DateInputValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "abc", "", "  ", "1일", "30일", "58일"})
    @DisplayName("입력 날짜가 숫자가 아닌 경우 예외가 발생한다.")
    public void 날짜_숫자_아닌_경우_테스트() throws Exception {
        assertThatThrownBy(() -> new User(dateInputValidator.validateInputDate("a"))).isInstanceOf(
                IllegalArgumentException.class);
    }

    @DisplayName("입력 날짜의 범위가 1 ~ 31일이 아닌 경우 예외가 발생한다.")
    @ValueSource(strings = {"32", "100", "0", "-1", "-100"})
    @ParameterizedTest
    public void 날짜_범위_테스트(String input) throws Exception {
        assertThatThrownBy(() -> new User(dateInputValidator.validateInputDate(input))).isInstanceOf(
                IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("입력 날짜가 null인 경우 예외가 발생한다.")
    public void 날짜_문자열_null_테스트(String input) throws Exception {
        assertThatThrownBy(() -> new User(dateInputValidator.validateInputDate(input))).isInstanceOf(
                IllegalArgumentException.class);
    }

    @Test
    @DisplayName("크리스마스 디데이 이벤트 날짜 테스트")
    public void 크리스마스_이벤트_날짜_테스트() throws Exception {
        //given
        User user1 = new User(dateInputValidator.validateInputDate("25"));
        User user2 = new User(dateInputValidator.validateInputDate("26"));
        //when
        boolean isEvent = user1.checkChristmasDate();
        boolean isNotEvent = user2.checkChristmasDate();
        //then
        assertEquals(true, isEvent);
        assertEquals(false, isNotEvent);
    }

    @Test
    public void 크리스마스_이벤트_할인_테스트() throws Exception {
        //given
        User user1 = new User(dateInputValidator.validateInputDate("25"));
        User user2 = new User(dateInputValidator.validateInputDate("2"));

        //when
        int discount1 = user1.checkChristmasEvent(EventDiscount.BASIC.getDiscountMoney());
        int discount2 = user2.checkChristmasEvent(EventDiscount.BASIC.getDiscountMoney());

        //then
        assertEquals(3400, discount1);
        assertEquals(1100,discount2);
    }



}
