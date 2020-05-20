package pl.training.shop.payments;

import lombok.Builder;
import lombok.Data;
import org.javamoney.moneta.FastMoney;

import java.time.Instant;

@Builder
@Data
public class Payment {

    private String id;
    private FastMoney money;
    private Instant timestamp;
    private PaymentStatus status;

}
