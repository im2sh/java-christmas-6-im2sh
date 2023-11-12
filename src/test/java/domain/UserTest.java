package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.Badge;
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
    @DisplayName("크리스마스 디데이 이벤트 할인 금액 테스트")
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

    @Test
    @DisplayName("평일 이벤트 날짜 테스트")
    public void 평일_이벤트_날짜_테스트() {
        //given
        User user1 = new User(dateInputValidator.validateInputDate("25"));
        User user2 = new User(dateInputValidator.validateInputDate("8"));
        //when
        boolean isEvent = user1.checkWeekDate();
        boolean isNotEvent = user2.checkWeekDate();
        //then
        assertEquals(true, isEvent);
        assertEquals(false, isNotEvent);
    }

    @Test
    @DisplayName("주말 이벤트 날짜 테스트")
    public void 주말_이벤트_날짜_테스트() {
        //given
        User user1 = new User(dateInputValidator.validateInputDate("2"));
        User user2 = new User(dateInputValidator.validateInputDate("28"));
        //when
        boolean isEvent = user1.checkWeekendDate();
        boolean isNotEvent = user2.checkWeekendDate();
        //then
        assertEquals(true, isEvent);
        assertEquals(false, isNotEvent);
    }

    @Test
    @DisplayName("특별 이벤트 날짜 테스트")
    public void 특별_이벤트_날짜_테스트() {
        //given
        User user1 = new User(dateInputValidator.validateInputDate("10"));
        User user2 = new User(dateInputValidator.validateInputDate("26"));
        //when
        boolean isEvent = user1.checkSpecialDate();
        boolean isNotEvent = user2.checkSpecialDate();
        //then
        assertEquals(true, isEvent);
        assertEquals(false, isNotEvent);
    }

    @Test
    @DisplayName("할인 금액에 따라 뱃지를 지급한다.")
    public void 유저_뱃지_발급_테스트() throws Exception {
        //given
        User user1 = new User(1);
        User user2 = new User(10);
        User user3 = new User(25);
        User user4 = new User(22);
        //when

        user1.receiveBadge(5500);
        user2.receiveBadge(15000);
        user3.receiveBadge(33000);
        user4.receiveBadge(2500);

        //then
        assertEquals(Badge.STAR, user1.getBadge());
        assertEquals(Badge.TREE, user2.getBadge());
        assertEquals(Badge.SANTA, user3.getBadge());
        assertEquals(Badge.NONE, user4.getBadge());
    }

}
