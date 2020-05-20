package pl.training.shop.payments;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FakePaymentServiceTest {

    private static final String PAYMENT_ID = "1";
    private static final Locale LOCALE = Locale.getDefault();
    private static final CurrencyUnit CURRENCY_UNIT = Monetary.getCurrency(LOCALE);
    private static final FastMoney MONEY = FastMoney.of(1_000, CURRENCY_UNIT);

    private final PaymentIdGenerator paymentIdGenerator = Mockito.mock(PaymentIdGenerator.class);
    private final FakePaymentService paymentService = new FakePaymentService(paymentIdGenerator);

    @BeforeEach
    void setUp() {
        when(paymentIdGenerator.getNext()).thenReturn(PAYMENT_ID);
    }

    @Test
    void shouldAssignIdToCreatedPayment() {
        var paymentRequest = PaymentRequest.builder()
                .money(MONEY)
                .build();
        var payment = paymentService.process(paymentRequest);
        assertEquals(PAYMENT_ID, payment.getId());
    }

}
