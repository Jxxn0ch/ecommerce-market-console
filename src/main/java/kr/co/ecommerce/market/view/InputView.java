package kr.co.ecommerce.market.view;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class InputView {
    public static String inputOrderOrQuit() {
        log.info("입력(o[order]: 주문, q[quit]: 종료) : ");
        return new Scanner(System.in).nextLine();
    }

    public static String inputProductCode() {
        log.info("상품번호 : ");
        return new Scanner(System.in).nextLine();
    }

    public static int inputProductQuantity() {
        log.info("수량 : ");
        return new Scanner(System.in).nextInt();
    }
}
