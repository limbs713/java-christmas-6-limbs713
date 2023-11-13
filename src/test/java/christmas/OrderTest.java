package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Constant.RegEx;
import christmas.domain.order.MenuItem;
import christmas.dto.OrderDto;
import christmas.repository.OrderRepository;
import christmas.service.OrderService;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderTest extends NsTest {
    private static final String DEFAULT_DATE = "1";
    private static final String DEFAULT_ORDER = "바비큐립-1";

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }

    @Nested
    class ValidOrderTest {
        @DisplayName("유효한 예약 날짜를 저장한다")
        @ValueSource(strings = {"31", "21", "1", "4", "17"})
        @ParameterizedTest
        void saveValidDate(String reserveDate) {
            //given
            OrderService orderService = new OrderService(new OrderRepository());
            OrderDto orderDto;

            //when
            orderDto = new OrderDto(orderService.createOrder(DEFAULT_ORDER), reserveDate);

            //then
            assertThat(orderDto.getReservationDate()).isEqualTo(Integer.parseInt(reserveDate));
        }

        @DisplayName("유효한 주문 메뉴와 수량을 저장한다")
        @ValueSource(strings = {
                "바비큐립-1",
                "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1",
                "타파스-1,제로콜라-1",
                "바비큐립-7,타파스-5,제로콜라-6",
                "바비큐립-10"
        })
        @ParameterizedTest
        void saveValidMenu(String menu) {
            //given
            OrderRepository orderRepository = new OrderRepository();
            OrderService orderService = new OrderService(orderRepository);
            Map<MenuItem, Integer> orderMap = new HashMap<>();
            Matcher matcher = RegEx.PARSING_MENU_REG_EX.getRegExPattern().matcher(menu);
            OrderDto orderDto;

            //when
            orderDto = new OrderDto(orderService.createOrder(menu), DEFAULT_DATE);
            while (matcher.find()) {
                orderMap.put(orderRepository.getMenuItemByName(matcher.group(1)), Integer.valueOf(matcher.group(2)));
            }

            //then
            assertThat(orderDto.getOrderMenu()).isEqualTo(orderMap);
        }
    }

    @Nested
    class invalidOrderTest {

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
        @ValueSource(strings = {
                "바비큐립-21",
                "안심스테이크-4,바비큐립-2",
                "제로콜라-5",
                "바비큐립-7, 타파스-5, 제로콜라-6",
                "바비큐립:10",
                "제로콜라-a",
                "\n",
                "바비큐 립 - 10"
        })
        @ParameterizedTest
        void invalidOrderMenuTest(String menu) {
            assertSimpleTest(() -> {
                runException(DEFAULT_DATE, menu);
                assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            });
        }
    }
}

