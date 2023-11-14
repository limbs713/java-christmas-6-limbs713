package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.constant.RegEx;
import christmas.order.domain.MenuItem;
import christmas.order.dto.OrderDto;
import christmas.order.repository.OrderRepository;
import christmas.order.service.OrderService;
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
}

