package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.time.Instant;

@Log
@RequiredArgsConstructor
public class FakePaymentService {

    private final PaymentIdGenerator paymentIdGenerator;

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
