# 🎄크리스마스 프로모션

## 📚 기능 요구 사항

- 주문 객체
  - 날짜를 입력받아 저장한다.
    - [예외 사항] 방문할 날짜는 1 이상 31 이하의 숫자여야 한다.
    - [예외 사항] 예외가 발생할 경우 **[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.** 라는 에러 메시지를 출력한다.
  - 주문할 메뉴와 개수를 입력받아 메뉴판 객체에 해당하는 메뉴 객체들을 찾도록 요청한다.
    - [예외 사항] 메뉴는 메뉴판에 있는 메뉴여야 한다.
    - [예외 사항] 메뉴의 개수는 1 이상의 숫자여야 한다.
    - [예외 사항] 음료만 주문할 수 없다.
    - [예외 사항] 주문 메뉴의 총 개수는 20개 이하여야 한다.
    - [예외 사항] 메뉴는 중복이 없어야 한다.
    - [예외 사항] 입력 형식이 미리 설정된 메뉴 형식과 일치해야한다.
    - [예외 사항] 예외가 발생할 경우 **[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.** 라는 에러 메시지를 출력한다.
  - 예약 날짜와 주문할 메뉴를 통해 주문을 요청한다.
- 메뉴판 객체
  - 매개변수로 받은 문자열을 이름으로 가지는 메뉴 객체를 찾아 반환한다.
    - [예외 사항] 일치하는 메뉴 이름이 없다면 IllegalArgumentException를 throw한다.
- 메뉴 객체
  - 매개변수로 받은 메뉴 이름에 해당하는 메뉴 객체를 생성한다.
- 예약 객체
  - 주문 객체로 부터 예약 날짜와 주문 메뉴 리스트를 받고 예상 금액과 예상 혜택을 반환한다.
    - 주문 메뉴 리스트를 통해 총 주문 금액을 계산한다.
    - 증정 메뉴가 추가됐다면, 해당하는 증정 이벤트를 할인 이벤트 리스트에 추가한다.
    - 예약 날짜에 해당하는 할인 이벤트들을 할인 이벤트 리스트에 추가한다.
    - 해당하는 할인 이벤트들을 통해 총 혜택 금액을 계산한다.
    - 총 주문 금액에 총 혜택 금액을 빼서 예상 결제 금액을 계산한다.
    - 총 혜택 금액을 통해 만족하는 이벤트 배치가 있다면 반환한다.
  
 ## 🌳 디렉토리 구조

    c:.
    ├─docs
    |   README.md
    └─src
    ├─main
    │  └─java
    │      └─christmas
    │          │  Application.java
    │          │
    │          ├─constant
    │          │      Days.java
    │          │      DayType.java
    │          │      EventBadge.java
    │          │      MenuType.java
    │          │      Message.java
    │          │      RegEx.java
    │          │
    │          ├─order
    │          │  ├─controller
    │          │  │      OrderController.java
    │          │  │
    │          │  ├─domain
    │          │  │      MenuBoard.java
    │          │  │      MenuItem.java
    │          │  │
    │          │  ├─dto
    │          │  │      OrderDto.java
    │          │  │
    │          │  ├─repository
    │          │  │      OrderRepository.java
    │          │  │
    │          │  ├─service
    │          │  │      OrderService.java
    │          │  │
    │          │  └─Validator
    │          │          MenuValidator.java
    │          │          ReservationDateValidator.java
    │          │
    │          ├─reserve
    │          │  ├─controller
    │          │  │      ReserveController.java
    │          │  │
    │          │  ├─domain
    │          │  │  │  DiscountBenefits.java
    │          │  │  │  Dish.java
    │          │  │  │  Receipt.java
    │          │  │  │  Reservation.java
    │          │  │  │
    │          │  │  └─Benefit
    │          │  │          BonusDishBenefit.java
    │          │  │          ChristmasBenefit.java
    │          │  │          DayTypeBenefit.java
    │          │  │          DiscountBenefit.java
    │          │  │          SpecialBenefit.java
    │          │  │
    │          │  ├─dto
    │          │  │      ReserveDto.java
    │          │  │
    │          │  ├─repository
    │          │  │      ReserveRepository.java
    │          │  │
    │          │  └─service
    │          │          ReserveService.java
    │          │
    │          └─view
    │                  InputView.java
    │                  OutputView.java
    │
    └─test
        └─java
            └─christmas
                    OrderTest.java
                    OutputTest.java
                    ReservationTest.java

## 📊 구현 기능 플로우 차트
![img_1.png](img_1.png)
