package kr.co.ecommerce.market.exception;

public class SoldOutException extends Exception {
    public SoldOutException(String code) {
        super(String.format("SoldOutException 발생. 상품번호 : %s 의 상품량이 재고량보다 큽니다.", code));
    }
}
