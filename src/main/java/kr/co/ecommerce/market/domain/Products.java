package kr.co.ecommerce.market.domain;


import kr.co.ecommerce.market.entity.Product;
import kr.co.ecommerce.market.exception.SoldOutException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

import static kr.co.ecommerce.market.view.OutputView.printWrongProductCode;

@Slf4j
public class Products {
    List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void printProductList() {
        log.info(String.format("%s\t%-33s%s\t\t%s","상품번호", "상품명", "판매가격", "재고수"));
        products.forEach(p -> log.info("{}\t\t{}\t\t{}\t\t{}", p.getCode(), p.getName(), p.getPrice(), p.getQuantity()));
    }

    public Cart validateProductCode(String code) {
        Cart cart = products.stream()
                .filter(p -> Integer.parseInt(code) == p.getCode())
                .findAny()
                .map(p -> Cart.builder()
                        .code(p.getCode())
                        .name(p.getName())
                        .price(p.getPrice())
                        .quantity(p.getQuantity())
                        .build())
                .orElse(null);

        if (ObjectUtils.isEmpty(cart)) {
            printWrongProductCode();
        }
        return cart;
    }

    public boolean validateProductQuantity(Carts carts) {
        // 재고 확인 리스트
        List<Integer> validateList = new ArrayList<>();
        products.forEach(p -> carts.getStream()
                .filter(c -> p.getCode() == c.getCode())
                .filter(c -> p.getQuantity() == 0 || p.getQuantity() < c.getQuantity())
                .map(Cart::getCode)
                .forEach(validateList::add));

        // 재고가 없는 상품 목록들을 화면에 출력
        if (!validateList.isEmpty()) {
            try {
                throw new SoldOutException(validateList.toString());
            } catch (SoldOutException e) {
                log.error("{}", e.toString());
                return false;
            }
        }
        return true;
    }
}
