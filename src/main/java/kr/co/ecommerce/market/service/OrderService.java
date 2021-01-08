package kr.co.ecommerce.market.service;

import kr.co.ecommerce.market.domain.Carts;

public interface OrderService {
    void createOrder(Carts carts);
}
