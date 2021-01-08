package kr.co.ecommerce.market.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private int code;
    private String name;
    private int price;
    private int quantity;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
