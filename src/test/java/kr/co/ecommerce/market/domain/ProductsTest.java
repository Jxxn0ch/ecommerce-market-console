package kr.co.ecommerce.market.domain;

import kr.co.ecommerce.market.exception.SoldOutException;
import kr.co.ecommerce.market.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ActiveProfiles("test")
@SpringBootTest
public class ProductsTest {
    @Autowired
    ProductRepository productRepository;
    Products products;

    @BeforeEach
    void init() {
        products = new Products(productRepository.findAllByDeleteYnOrderByCodeDesc("N"));
    }

    @Test
    void 상품_목록_출력() {
        products.printProductList();
    }

    @Test
    void 유효한_상품번호인지_유효성_검사() {
        assertThat(products.validateProductCode("377169")).isNotNull();
    }

    @Test
    void 재고가_있는지_유효성_검사() {
        Carts carts = new Carts();
        carts.addCart(Cart.builder()
                .code(778422)
                .name("캠핑덕 우드롤테이블")
                .price(45000)
                .quantity(7)
                .build());
        carts.addCart(Cart.builder()
                .code(648418)
                .name("BS 02-2A DAYPACK 26 (BLACK)")
                .price(238000)
                .quantity(5)
                .build());

        assertThat(products.validateProductQuantity(carts)).isEqualTo(true);
    }

    @Test
    void SoldOutException_작동_확인() {
        assertThat(catchThrowable(() -> { throw new SoldOutException("778422"); }))
                .isInstanceOf(SoldOutException.class)
                .hasMessageContaining("SoldOutException");
    }
}
