package pl.training.shop;

import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;
import pl.training.shop.payments.FakePaymentsService;
import pl.training.shop.payments.PaymentRequest;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Locale;

@Log
public class Application {

    private static final Locale LOCALE = Locale.getDefault();
    private static final CurrencyUnit currencyUnit = Monetary.getCurrency(LOCALE);
    private static final FastMoney money = FastMoney.of(1_000, currencyUnit);

    public static void main(String[] args) {
        var paymentsService = new FakePaymentsService();
        var paymentRequest = PaymentRequest.builder()
                .money(money)
                .build();
        var payment = paymentsService.process(paymentRequest);
        log.info(payment.toString());
    }

}