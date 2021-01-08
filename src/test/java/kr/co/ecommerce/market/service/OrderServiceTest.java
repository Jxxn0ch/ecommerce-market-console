package kr.co.ecommerce.market.service;

import kr.co.ecommerce.market.domain.Cart;
import kr.co.ecommerce.market.domain.Carts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.Executor;

import static org.assertj.core.api.Assertions.assertThatCode;

@ActiveProfiles("test")
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Qualifier("asyncExecutor")
    @Autowired
    private Executor executor;

    @Test
    void 주문_생성_성공() {
        Carts carts = new Carts();
        carts.addCart(Cart.builder()
                .code(768848)
                .name("[STANLEY] GO CERAMIVAC 진공 텀블러/보틀 3종")
                .price(21000)
                .quantity(2)
                .build());
        carts.addCart(Cart.builder()
                .code(748943)
                .name("디오디너리 데일리 세트 (Daily set)")
                .price(19000)
                .quantity(3)
                .build());

        assertThatCode(() -> orderService.createOrder(carts))
                .doesNotThrowAnyException();
    }

    @Test
    void 다중_쓰레드로_SoldOutException() throws InterruptedException {
        Carts carts = new Carts();
        carts.addCart(Cart.builder()
                .code(778422)
                .name("캠핑덕 우드롤테이블")
                .price(45000)
                .quantity(2)
                .build());

        for (int i = 0; i < 4; i++) {
            executor.execute(() -> orderService.createOrder(carts));
        }

        Thread.sleep(1000);
    }
}
