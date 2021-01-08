package kr.co.ecommerce.market.view;

import kr.co.ecommerce.market.domain.Cart;
import kr.co.ecommerce.market.domain.Carts;
import kr.co.ecommerce.market.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static kr.co.ecommerce.market.view.OutputView.printReceipt;

@ActiveProfiles("test")
@SpringBootTest
public class OutputViewTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void 영수증_출력() {
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

        printReceipt(carts);
    }
}
