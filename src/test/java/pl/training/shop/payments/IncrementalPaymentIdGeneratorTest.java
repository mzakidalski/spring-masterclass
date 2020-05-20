package pl.training.shop.payments;

import org.junit.jupiter.api.Test;

import static java.lang.Long.parseLong;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IncrementalPaymentIdGeneratorTest {

    private static final String ID_FORMAT = "\\d{10}";

    private final IncrementalPaymentIdGenerator paymentIdGenerator = new IncrementalPaymentIdGenerator();

    @Test
    void shouldGenerateValidId() {
        String id = paymentIdGenerator.getNext();
        assertTrue(id.matches(ID_FORMAT));
    }

    @Test
    void shouldGenerateIdIncreasingPreviousOne() {
        long firstIdValue = parseLong(paymentIdGenerator.getNext());
        long secondIdValue = parseLong(paymentIdGenerator.getNext());
        assertEquals(firstIdValue + 1, secondIdValue);
    }

}
