package pl.training.shop.payments;

import lombok.extern.java.Log;
import org.springframework.context.ApplicationListener;

@Log
public class PaymentStatusChangeListener implements ApplicationListener<PaymentStatusChangedEvent> {

    @Override
    public void onApplicationEvent(PaymentStatusChangedEvent statusChangedEvent) {
        log.info("Payment changed status: " + statusChangedEvent.getPayment());
    }

}
