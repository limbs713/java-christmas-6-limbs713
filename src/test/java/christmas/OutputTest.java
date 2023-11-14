package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class OutputTest extends NsTest {
    private static final String DEFAULT_DATE = "1";

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("<주문 메뉴>", "<할인 전 총주문 금액>", "<증정 메뉴>", "<혜택 내역>", "<총혜택 금액>",
                    "<할인 후 예상 결제 금액>", "<12월 이벤트 배지>");
        });
    }

    @DisplayName("유효하지 않은 예약 날짜는 error 메시지를 출력한다")
    @ValueSource(strings = {"50", "-11", "32", "a", "\n", "!", " 1", "2 1", "3,2"})
    @ParameterizedTest
    void invalidReserveDateTest(String reserveDate) {
        assertSimpleTest(() -> {
            runException(reserveDate);
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @DisplayName("유효하지 않은 주문 메뉴는 error 메시지를 출력한다")
    @ValueSource(strings = {"바비큐립-21", "안심스테이크-4,바비큐립-2", "제로콜라-5", "바비큐립-7, 타파스-5, 제로콜라-6", "바비큐립:10", "제로콜라-a", "\n",
            "바비큐 립 - 10"})
    @ParameterizedTest
    void invalidOrderMenuTest(String menu) {
        assertSimpleTest(() -> {
            runException(DEFAULT_DATE, menu);
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Nested
    class outputBenefitTest {

        @DisplayName("할인 혜택이 적용된다면 혜택 내역에 포함해야한다")
        @CsvSource(value = {"24:티본스테이크-3,제로콜라-5,아이스크림-2:평일 할인", "23:제로콜라-3,타파스-5,레드와인-2,바비큐립-3:주말 할인",
                "24:해산물파스타-3,레드와인-1:크리스마스 디데이 할인", "25:크리스마스파스타-3,제로콜라-3,양송이수프-3:특별 할인",
                "26:타파스-1,제로콜라-1 :없음"}, delimiter = ':')
        @ParameterizedTest
        void BenefitSpecificationTest(String reserveDate, String menu, String eventName) {
            assertSimpleTest(() -> {
                runException(reserveDate, menu);
                assertThat(output()).contains(eventName);
            });
        }

        @DisplayName("혜택 금액이 0원이면 해당하는 혜택이라도 혜택 내역에서 제외해야한다.")
        @CsvSource(value = {"24:티본스테이크-3,제로콜라-5:평일 할인", "23:제로콜라-3,타파스-5,레드와인-2:주말 할인",
                "26:해산물파스타-3,레드와인-1:크리스마스 디데이 할인", "29:크리스마스파스타-3:제로콜라-3,양송이수프-3:특별 할인"}, delimiter = ':')
        @ParameterizedTest
        void noBenefitSpecificationTest(String reserveDate, String menu, String eventName) {
            assertSimpleTest(() -> {
                runException(reserveDate, menu);
                assertThat(output()).doesNotContain(eventName);
            });
        }
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
