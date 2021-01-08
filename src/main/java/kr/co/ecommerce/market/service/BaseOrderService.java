package kr.co.ecommerce.market.service;

import kr.co.ecommerce.market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseOrderService {
    @Autowired
    protected ProductRepository productRepository;
}
