package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;

import java.util.Locale;

@Log
@RequiredArgsConstructor
public class PaymentConsoleLogger implements Ordered {

    private static final String MESSAGE_KEY = "paymentInfo";

    private final MessageSource messageSource;

    public void beforePayment(PaymentRequest paymentRequest) {
        log.info("New payment request: " + paymentRequest);
    }

    public void afterPayment() {
        log.info("After payment");
    }

    public void onException(Exception exception) {
        log.info("Payment exception: " + exception.getClass().getSimpleName());
    }

    public void log(Payment payment) {
        log.info(createLogEntry(payment));
    }

    private String createLogEntry(Payment payment) {
        return messageSource.getMessage(MESSAGE_KEY, new String[] { payment.getMoney().toString() }, Locale.getDefault());
    }

    @Override
    public int getOrder() {
        return 50;
    }

}
