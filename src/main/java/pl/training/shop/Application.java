package pl.training.shop;

import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;
import pl.training.shop.payments.FakePaymentService;
import pl.training.shop.payments.IncrementalPaymentIdGenerator;
import pl.training.shop.payments.LoggingPaymentService;
import pl.training.shop.payments.PaymentRequest;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Locale;

@Log
public class Application {

    private static final Locale LOCALE = Locale.getDefault();
    private static final CurrencyUnit CURRENCY_UNIT = Monetary.getCurrency(LOCALE);
    private static final FastMoney MONEY = FastMoney.of(1_000, CURRENCY_UNIT);

    public static void main(String[] args) {
        var paymentIdGenerator = new IncrementalPaymentIdGenerator();
        var fakePaymentService = new FakePaymentService(paymentIdGenerator);
        var paymentService = new LoggingPaymentService(fakePaymentService);
        var paymentRequest = PaymentRequest.builder()
                .money(MONEY)
                .build();
        var payment = paymentService.process(paymentRequest);
        log.info(payment.toString());
    }

}
