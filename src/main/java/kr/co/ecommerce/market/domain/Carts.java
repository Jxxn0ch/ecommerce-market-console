package kr.co.ecommerce.market.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Slf4j
public class Carts {
    private List<Cart> carts = new ArrayList<>();

    public void addCart(Cart cart) {
        carts.add(cart);
    }

    public long getTotalPrice() {
        return carts.stream()
                .mapToLong(c -> (long) c.getPrice() * (long) c.getQuantity())
                .reduce(Long::sum)
                .orElse(0);
    }

    public Stream<Cart> getStream() {
        return carts.stream();
    }

    public void printItems() {
        carts.forEach(c -> log.info("{} - {}개", c.getName(), c.getQuantity()));
    }

    public boolean isEmpty() {
        return carts.isEmpty();
    }
}
