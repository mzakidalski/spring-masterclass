package pl.training.shop;

import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.training.shop.payments.PaymentRequest;
import pl.training.shop.payments.PaymentService;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Locale;

@Log
public class Application {

    private static final String BASE_PACKAGE = "pl.training.shop";
    private static final Locale LOCALE = Locale.getDefault();
    private static final CurrencyUnit CURRENCY_UNIT = Monetary.getCurrency(LOCALE);
    private static final FastMoney MONEY = FastMoney.of(1_000, CURRENCY_UNIT);

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BASE_PACKAGE)) {
            var paymentService = applicationContext.getBean(PaymentService.class);
            var paymentRequest = PaymentRequest.builder()
                    .money(MONEY)
                    .build();
            var payment = paymentService.process(paymentRequest);
            log.info(payment.toString());
        }
    }

}
