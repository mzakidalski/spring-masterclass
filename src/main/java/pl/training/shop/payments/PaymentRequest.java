package pl.training.shop.payments;

import lombok.Builder;
import lombok.Data;
import org.javamoney.moneta.FastMoney;

@Builder
@Data
public class PaymentRequest {

    private Long id;
    private FastMoney money;

}
