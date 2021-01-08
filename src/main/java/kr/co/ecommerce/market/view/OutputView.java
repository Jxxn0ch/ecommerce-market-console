package kr.co.ecommerce.market.view;

import kr.co.ecommerce.market.domain.Carts;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;

@Slf4j
public class OutputView {
    static final String SEPARATOR = "-----------------------------------------";
    static final String AMOUNT_PATTERN = "###,###";

    public static void printReceipt(Carts carts) {
        log.info("주문내역 : ");
        log.info(SEPARATOR);
        carts.printItems();
        log.info(SEPARATOR);
        log.info("주문금액 : {}", longToCustomFormat(carts.getTotalPrice()));
        if(carts.getTotalPrice() < 50000) {
            log.info("배송비 : {}", longToCustomFormat(5000));
        }
        log.info(SEPARATOR);
        log.info("지불금액 : {}", carts.getTotalPrice() < 50000 ? longToCustomFormat(carts.getTotalPrice() + 5000) : longToCustomFormat(carts.getTotalPrice()));
        log.info(SEPARATOR);
    }

    public static void printQuit() {
        log.info("고객님의 주문 감사합니다.");
    }

    public static void printWrongProductCode() {
        log.info("잘못된 상품번호입니다.");
    }

    public static void printWrongWord() {
        log.info("잘못 입력하였습니다.");
    }

    private static String longToCustomFormat(long amount) {
        return new DecimalFormat(AMOUNT_PATTERN).format(amount);
    }
}
