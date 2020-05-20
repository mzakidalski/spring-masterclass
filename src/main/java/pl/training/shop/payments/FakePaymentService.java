package pl.training.shop.payments;

import lombok.extern.java.Log;

import java.time.Instant;

@Log
public class FakePaymentService {

    private final UUIDPaymentIdGenerator paymentIdGenerator = new UUIDPaymentIdGenerator();

    public Payment process(PaymentRequest paymentRequest) {
        var payment = Payment.builder()
                .id(paymentIdGenerator.getNext())
                .money(paymentRequest.getMoney())
                .timestamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
        log.info(String.format("A new payment of %s has been initiated", payment.getMoney()));
        return payment;
    }

}
