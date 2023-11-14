package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.domain.order.MenuItem;
import christmas.domain.reserve.Reservation;
import christmas.repository.OrderRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ReservationTest extends NsTest {
    private static final int DEFAULT_QUANTITY = 3;
    private static final int DEFAULT_DATE = 1;
    private static final String DEFAULT_MENU = "양송이수프,티본스테이크,초코케이크,제로콜라";


    @DisplayName("주문을 받아 주문 메뉴에 대한 할인전 결제 금액을 계산한다.")
    @ValueSource(strings = {
            "바비큐립,제로콜라",
            "양송이수프,타파스,티본스테이크,아이스크림",
            "티본스테이크,제로콜라,레드와인,샴페인",
            "해산물파스타"
    })
    @ParameterizedTest
    void orderPriceTest(String menuName) {
        //given
        OrderRepository orderRepository = new OrderRepository();
        List<String> items = List.of(menuName.split(","));
        Reservation reservation;
        Map<MenuItem, Integer> menuItems = new HashMap<>();
        int totalOrderPrice;

        //when
        items.forEach(menuItem -> menuItems.put(orderRepository.getMenuItemByName(menuItem), DEFAULT_QUANTITY));
        reservation = new Reservation(menuItems, DEFAULT_DATE);
        totalOrderPrice = menuItems.keySet().stream()
                .mapToInt(MenuItem::getPrice)
                .sum();

        //then

        assertThat(reservation.getTotalOrderPrice()).isEqualTo(totalOrderPrice * DEFAULT_QUANTITY);
    }

    @Nested
    class BonusMenuTest {

        @DisplayName("주문 금액이 120,000원이 넘어가면 증정 이벤트를 제공해야한다")
        @ValueSource(strings = {
                "양송이수프,타파스,티본스테이크,아이스크림",
                "티본스테이크,제로콜라,레드와인,샴페인",
        })
        @ParameterizedTest
        void bonusMenuTest(String menuName) {
            //given
            OrderRepository orderRepository = new OrderRepository();
            List<String> items = List.of(menuName.split(","));
            Reservation reservation;
            Map<MenuItem, Integer> menuItems = new HashMap<>();

            //when
            items.forEach(menuItem -> menuItems.put(orderRepository.getMenuItemByName(menuItem), DEFAULT_QUANTITY));
            reservation = new Reservation(menuItems, DEFAULT_DATE);

            //then

            assertThat(reservation.getBenefitSpecification().keySet()).contains("증정 이벤트");
        }

        @DisplayName("주문 금액이 120,000원 이하라면 증정 이벤트를 제공하지 않는다")
        @ValueSource(strings = {
                "아이스크림",
                "샴페인,양송이수프",
                "해산물파스타"
        })
        @ParameterizedTest
        void noBonusMenuTest(String menuName) {
            //given
            OrderRepository orderRepository = new OrderRepository();
            List<String> items = List.of(menuName.split(","));
            Reservation reservation;
            Map<MenuItem, Integer> menuItems = new HashMap<>();

            //when
            items.forEach(menuItem -> menuItems.put(orderRepository.getMenuItemByName(menuItem), DEFAULT_QUANTITY));
            reservation = new Reservation(menuItems, DEFAULT_DATE);

            //then

            assertThat(reservation.getBenefitSpecification().keySet()).doesNotContain("증정 이벤트");
        }
    }

    @Nested
    class DayTypeTest {

        @DisplayName("예약 날짜가 평일이면 평일 이벤트를 혜택 목록에 추가해야한다")
        @ValueSource(ints = {
                3,
                4,
                5,
                6,
                7,
                10,
                11,
                25
        })
        @ParameterizedTest
        void weekDayTest(int reserveDate) {
            //given
            OrderRepository orderRepository = new OrderRepository();
            List<String> items = List.of(DEFAULT_MENU.split(","));
            Reservation reservation;
            Map<MenuItem, Integer> menuItems = new HashMap<>();

            //when
            items.forEach(menuItem -> menuItems.put(orderRepository.getMenuItemByName(menuItem), DEFAULT_QUANTITY));
            reservation = new Reservation(menuItems, reserveDate);

            //then

            assertThat(reservation.getBenefitSpecification().keySet()).contains("평일 할인");
        }

        @DisplayName("예약 날짜가 주말이면 주말 이벤트를 혜택 목록에 추가해야한다")
        @ValueSource(ints = {
                1, 2, 8, 9, 23
        })
        @ParameterizedTest
        void weekendTest(int reserveDate) {
            //given
            OrderRepository orderRepository = new OrderRepository();
            List<String> items = List.of(DEFAULT_MENU.split(","));
            Reservation reservation;
            Map<MenuItem, Integer> menuItems = new HashMap<>();

            //when
            items.forEach(menuItem -> menuItems.put(orderRepository.getMenuItemByName(menuItem), DEFAULT_QUANTITY));
            reservation = new Reservation(menuItems, reserveDate);

            //then

            assertThat(reservation.getBenefitSpecification().keySet()).contains("주말 할인");
        }
    }

    @Nested
    class SpecialDiscountTest {

        @DisplayName("예약 날짜가 일요일 혹은 크리스마스이면 특별 할인을 추가해야한다.")
        @ValueSource(ints = {
                3,
                10,
                17,
                24,
                25,
                31
        })
        @ParameterizedTest
        void specialDiscountTest(int reserveDate) {
            //given
            OrderRepository orderRepository = new OrderRepository();
            List<String> items = List.of(DEFAULT_MENU.split(","));
            Reservation reservation;
            Map<MenuItem, Integer> menuItems = new HashMap<>();

            //when
            items.forEach(menuItem -> menuItems.put(orderRepository.getMenuItemByName(menuItem), DEFAULT_QUANTITY));
            reservation = new Reservation(menuItems, reserveDate);

            //then

            assertThat(reservation.getBenefitSpecification().keySet()).contains("특별 할인");
        }

        @DisplayName("예약 날짜가 일요일 혹은 크리스마스가 아니라면 특별 할인을 추가하지 않는다.")
        @ValueSource(ints = {
                1, 2, 4, 5, 6, 7, 15, 23, 26
        })
        @ParameterizedTest
        void noSpecialDiscountTest(int reserveDate) {
            //given
            OrderRepository orderRepository = new OrderRepository();
            List<String> items = List.of(DEFAULT_MENU.split(","));
            Reservation reservation;
            Map<MenuItem, Integer> menuItems = new HashMap<>();

            //when
            items.forEach(menuItem -> menuItems.put(orderRepository.getMenuItemByName(menuItem), DEFAULT_QUANTITY));
            reservation = new Reservation(menuItems, reserveDate);

            //then

            assertThat(reservation.getBenefitSpecification().keySet()).doesNotContain("특별 할인");
        }
    }

    @Nested
    class ChristmasDiscountTest {

        @DisplayName("예약 날짜가 크리스마스 이전이면 크리스마스 디데이 할인이 추가돼야 한다. "
                + "또한 디데이 할인 가격이 정확한 지 테스트한다")
        @ValueSource(ints = {
                3, 6, 8, 10, 16, 19, 23, 25
        })
        @ParameterizedTest
        void christmasDiscountTest(int reserveDate) {
            //given
            OrderRepository orderRepository = new OrderRepository();
            List<String> items = List.of(DEFAULT_MENU.split(","));
            Reservation reservation;
            Map<MenuItem, Integer> menuItems = new HashMap<>();

            //when
            items.forEach(menuItem -> menuItems.put(orderRepository.getMenuItemByName(menuItem), DEFAULT_QUANTITY));
            reservation = new Reservation(menuItems, reserveDate);

            //then

            assertThat(reservation.getBenefitSpecification().keySet()).contains("크리스마스 디데이 할인");
            assertThat(reservation.getBenefitSpecification().get("크리스마스 디데이 할인")).isEqualTo(
                    (reserveDate - 1) * 100 + 1000);
        }

        @DisplayName("예약 날짜가 크리스마스 이후라면 크리스마스 디데이 할인을 추가하지 않는다.")
        @ValueSource(ints = {
                26, 27, 28, 29, 30, 31
        })
        @ParameterizedTest
        void noChristMasDiscountTest(int reserveDate) {
            //given
            OrderRepository orderRepository = new OrderRepository();
            List<String> items = List.of(DEFAULT_MENU.split(","));
            Reservation reservation;
            Map<MenuItem, Integer> menuItems = new HashMap<>();

            //when
            items.forEach(menuItem -> menuItems.put(orderRepository.getMenuItemByName(menuItem), DEFAULT_QUANTITY));
            reservation = new Reservation(menuItems, reserveDate);

            //then

            assertThat(reservation.getBenefitSpecification().keySet()).doesNotContain("크리스마스 디데이 할인");
        }
    }

    @Nested
    class EventBadgeTest {
        @DisplayName("총혜택 금액이 5000원 이하라면 뱃지를 부여하지 않는다")
        @ValueSource(ints = {1, 2, 3, 4, 5})
        @ParameterizedTest
        void noBadgeTest(int reserveDate) {
            //given
            OrderRepository orderRepository = new OrderRepository();
            List<String> items = List.of(DEFAULT_MENU.split(","));
            Reservation reservation;
            Map<MenuItem, Integer> menuItems = new HashMap<>();

            //when
            items.forEach(menuItem -> menuItems.put(orderRepository.getMenuItemByName(menuItem), 1));
            reservation = new Reservation(menuItems, reserveDate);

            //then

            assertThat(reservation.getEventBadge()).isEqualTo("없음");
        }

        @DisplayName("총혜택 금액이 5000원 이상 10000원 이하이면 별 뱃지를 부여한다")
        @ValueSource(ints = {21, 22, 23, 24, 25})
        @ParameterizedTest
        void starBadgeTest(int reserveDate) {
            //given
            OrderRepository orderRepository = new OrderRepository();
            List<String> items = List.of(DEFAULT_MENU.split(","));
            Reservation reservation;
            Map<MenuItem, Integer> menuItems = new HashMap<>();

            //when
            items.forEach(menuItem -> menuItems.put(orderRepository.getMenuItemByName(menuItem), 1));
            reservation = new Reservation(menuItems, reserveDate);

            //then

            assertThat(reservation.getEventBadge()).isEqualTo("별");
        }

        @DisplayName("총혜택 금액이 10000원 이상 20000원 이하라면 트리 뱃지를 부여한다")
        @CsvSource(value = {
                "초코케이크:26:5",
                "아이스크림:21:5",
                "크리스마스파스타:23:4"},
                delimiter = ':'
        )
        @ParameterizedTest
        void treeBadgeTest(String menu, int reserveDate, int quantity) {
            //given
            OrderRepository orderRepository = new OrderRepository();
            List<String> items = List.of(menu.split(","));
            Reservation reservation;
            Map<MenuItem, Integer> menuItems = new HashMap<>();

            //when
            items.forEach(menuItem -> menuItems.put(orderRepository.getMenuItemByName(menuItem), quantity));
            reservation = new Reservation(menuItems, reserveDate);

            //then

            assertThat(reservation.getEventBadge()).isEqualTo("트리");
        }

        @DisplayName("총혜택 금액이 20000원 이상이라면 산타 뱃지를 부여한다")
        @CsvSource(value = {
                "초코케이크:26:10",
                "아이스크림,해산물파스타:21:5",
                "크리스마스파스타,레드와인:23:4"},
                delimiter = ':'
        )
        @ParameterizedTest
        void santaBadgeTest(String menu, int reserveDate, int quantity) {
            //given
            OrderRepository orderRepository = new OrderRepository();
            List<String> items = List.of(menu.split(","));
            Reservation reservation;
            Map<MenuItem, Integer> menuItems = new HashMap<>();

            //when
            items.forEach(menuItem -> menuItems.put(orderRepository.getMenuItemByName(menuItem), quantity));
            reservation = new Reservation(menuItems, reserveDate);

            //then

            assertThat(reservation.getEventBadge()).isEqualTo("산타");
        }
    }

    @DisplayName("총혜택 금액을 정확하게 계산하는 지 테스트")
    @CsvSource(value = {
            "초코케이크:26:5",
            "아이스크림:21:5",
            "크리스마스파스타:23:4"},
            delimiter = ':'
    )
    @ParameterizedTest
    void totalDiscountPriceTest(String menu, int reserveDate, int quantity) {
        //given
        OrderRepository orderRepository = new OrderRepository();
        List<String> items = List.of(menu.split(","));
        Reservation reservation;
        Map<MenuItem, Integer> menuItems = new HashMap<>();
        int totalBenefitPrice;
        //when
        items.forEach(menuItem -> menuItems.put(orderRepository.getMenuItemByName(menuItem), quantity));
        reservation = new Reservation(menuItems, reserveDate);

        totalBenefitPrice = reservation.getBenefitSpecification().values().stream()
                .mapToInt(value -> value)
                .sum();

        //then
        assertThat(reservation.getTotalBenefitPrice()).isEqualTo(totalBenefitPrice);
    }

    @DisplayName("최종 결제 금액을 정확하게 계산하는 지 테스트")
    @CsvSource(value = {
            "초코케이크:26:5",
            "아이스크림:21:5",
            "크리스마스파스타:23:7"},
            delimiter = ':'
    )
    @ParameterizedTest
    void finalOrderPriceAfterDiscountTest(String menu, int reserveDate, int quantity) {
        //given
        OrderRepository orderRepository = new OrderRepository();
        List<String> items = List.of(menu.split(","));
        Reservation reservation;
        Map<MenuItem, Integer> menuItems = new HashMap<>();
        int actualBenefitPrice;
        int finalOrderPrice;
        //when
        items.forEach(menuItem -> menuItems.put(orderRepository.getMenuItemByName(menuItem), quantity));
        reservation = new Reservation(menuItems, reserveDate);

        actualBenefitPrice = reservation.getBenefitSpecification().entrySet().stream()
                .filter(entry -> !entry.getKey().equals("증정 이벤트"))
                .mapToInt(Entry::getValue)
                .sum();

        finalOrderPrice = menuItems.keySet().stream()
                .mapToInt(MenuItem::getPrice)
                .sum();

        //then
        assertThat(reservation.getTotalOrderPrice() - reservation.getActualDiscountPrice())
                .isEqualTo(finalOrderPrice * quantity - actualBenefitPrice);
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
