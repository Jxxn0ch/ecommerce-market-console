package kr.co.ecommerce.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MarketApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MarketApplication.class, args);
        SpringApplication.exit(ctx, () -> 0);
    }
}
