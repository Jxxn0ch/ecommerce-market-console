package kr.co.ecommerce.market.service;

import kr.co.ecommerce.market.domain.Cart;
import kr.co.ecommerce.market.domain.Carts;
import kr.co.ecommerce.market.domain.Products;
import kr.co.ecommerce.market.view.OutputView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl extends BaseOrderService implements OrderService{

    /**
     * 주문 생성
     *
     * @param carts
     */
    @Transactional
    @Override
    public void createOrder(Carts carts) {
        // 선택한 상품 코드들의 데이터 추출
        Products products = new Products(productRepository.findAllByCodeInAndDeleteYn(
                carts.getStream()
                        .map(Cart::getCode)
                        .collect(Collectors.toList()), "N"));

        // 상품 재고확인
        if(products.validateProductQuantity(carts)) {
            // 계산서 출력
            OutputView.printReceipt(carts);
            // 재고 업데이트
            updateProductQuantity(products, carts);
        }
    }

    /**
     * 상품 재고 업데이트
     *
     * @param products
     * @param carts
     */
    private void updateProductQuantity(Products products, Carts carts) {
        products.getProducts().forEach(p -> carts.getStream()
                .filter(c -> p.getCode() == c.getCode())
                .forEach(c -> p.setQuantity(p.getQuantity() - c.getQuantity())));

        productRepository.saveAll(products.getProducts());
    }
}