package pl.inpost.shopping.core.policy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.inpost.shopping.core.policy.exception.NegativeAmountException;
import pl.inpost.shopping.core.policy.exception.NegativePriceException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.inpost.shopping.mock.TestConstants.*;

class AmountBasedDiscountPolicyTest {
    private static final BigDecimal BASE_PRICE = BigDecimal.valueOf(1000);

    Map<Integer, BigDecimal> discounts = Map.ofEntries(
            Map.entry(10, DISCOUNT_15),
            Map.entry(5, DISCOUNT_10),
            Map.entry(0, DISCOUNT_05)
    );

    AmountBasedDiscountPolicyProperties properties = new AmountBasedDiscountPolicyProperties(new TreeMap<>(discounts));
    AmountBasedDiscountPolicy objectUnderTest = new AmountBasedDiscountPolicy(properties);

    @ParameterizedTest
    @MethodSource
    void shouldApplyCorrectDiscountForGivenAmount(Integer amount, BigDecimal expectedDiscount) {
        // when
        BigDecimal actualDiscount = objectUnderTest.calculateDiscount(BASE_PRICE, amount);

        // then
        assertEquals(expectedDiscount, actualDiscount);
    }

    static Stream<Arguments> shouldApplyCorrectDiscountForGivenAmount() {
        return Stream.of(
                Arguments.of(0, BASE_PRICE.multiply(DISCOUNT_05)),
                Arguments.of(1, BASE_PRICE.multiply(DISCOUNT_05)),
                Arguments.of(6, BASE_PRICE.multiply(DISCOUNT_10)),
                Arguments.of(10, BASE_PRICE.multiply(DISCOUNT_15)),
                Arguments.of(99, BASE_PRICE.multiply(DISCOUNT_15))
        );
    }

    @Test
    void shouldNotBeApplicableForNegativeAmounts() {
        // given
        Integer amount = -1;

        // when
        Throwable exception = assertThrows(
                NegativeAmountException.class,
                () -> objectUnderTest.calculateDiscount(BASE_PRICE, amount)
        );

        // then
        assertEquals("Discount cannot be applied to negative amounts.", exception.getMessage());
    }

    @Test
    void shouldNotBeApplicableForNegativePrices() {
        // given
        BigDecimal negativePrice = BigDecimal.valueOf(-500);
        Integer amount = 1;

        // when
        Throwable exception = assertThrows(
                NegativePriceException.class,
                () -> objectUnderTest.calculateDiscount(negativePrice, amount)
        );

        // then
        assertEquals("Discount cannot be applied to negative prices.", exception.getMessage());
    }
}