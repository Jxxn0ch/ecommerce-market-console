package kr.co.ecommerce.market.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class CartsTest {
    Carts carts;

    @BeforeEach
    void init() {
        carts = new Carts();
        carts.addCart(Cart.builder()
                .code(782858)
                .name("폴로 랄프로렌 남성 수영복반바지 컬렉션 (51color)")
                .price(39500)
                .quantity(3)
                .build());
        carts.addCart(Cart.builder()
                .code(744775)
                .name("SHUT UP [TK00112]")
                .price(28000)
                .quantity(2)
                .build());
    }

    @Test
    void 카트_물품_총금액() {
        assertThat(carts.getTotalPrice()).isEqualTo(174500);
    }

    @Test
    void 카트_현재_담긴_물품() {
        carts.printItems();
    }

    @Test
    void 카트_물품이_비었는지_확인() {
        assertThat(carts.isEmpty()).isEqualTo(false);
    }
}
