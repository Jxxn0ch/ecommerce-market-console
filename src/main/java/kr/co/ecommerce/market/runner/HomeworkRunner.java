package kr.co.ecommerce.market.runner;

import kr.co.ecommerce.market.domain.Cart;
import kr.co.ecommerce.market.domain.Carts;
import kr.co.ecommerce.market.domain.Products;
import kr.co.ecommerce.market.repository.ProductRepository;
import kr.co.ecommerce.market.service.OrderService;
import kr.co.ecommerce.market.view.InputView;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static kr.co.ecommerce.market.view.OutputView.printQuit;
import static kr.co.ecommerce.market.view.OutputView.printWrongWord;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class HomeworkRunner implements CommandLineRunner {
    private final OrderService orderService;
    private final ProductRepository productRepository;

    /**
     * 메인화면
     *
     * @param args
     */
    @Override
    public void run(String... args) {
        boolean isRunning = true;
        while (isRunning) {
            switch(InputView.inputOrderOrQuit()) {
                case "o" : startOrder();
                    break;
                case "q" : printQuit();
                    isRunning = false;
                    break;
                default:
                    printWrongWord();
            }
        }
        System.exit(0);
    }

    /**
     * 주문정보입력
     *
     */
    private void startOrder() {
        Products products = new Products(productRepository.findAllByDeleteYnOrderByCodeDesc("N"));
        products.printProductList();
        Carts carts = new Carts();
        while(true) {
            String code = InputView.inputProductCode();
            // 스페이스 입력 시
            if(StringUtils.isBlank(code)) {
                if(!carts.isEmpty()) {
                    orderService.createOrder(carts);
                }
                break;
            }
            // 상품번호 입력 시
            Cart cartDto = products.validateProductCode(code);
            if(cartDto != null) {
                int quantity = InputView.inputProductQuantity();
                cartDto.setQuantity(quantity);
                carts.addCart(cartDto);
            }
        }
    }
}
