package pl.inpost.shopping.core.policy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.inpost.shopping.domain.exception.NegativePriceException;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.inpost.shopping.testutils.TestConstants.DISCOUNT_15;

class PercentageBasedDiscountPolicyTest {
    private static final BigDecimal BASE_PRICE = BigDecimal.valueOf(1000);

    PercentageBasedDiscountPolicyProperties properties = new PercentageBasedDiscountPolicyProperties(DISCOUNT_15);
    PercentageBasedDiscountPolicy objectUnderTest = new PercentageBasedDiscountPolicy(properties);

    @ParameterizedTest
    @MethodSource
    void shouldApplyStaticPercentageDiscountRegardlessOfAmount(Integer amount, BigDecimal expectedDiscount) {
        // when
        BigDecimal actualDiscount = objectUnderTest.calculateDiscount(BASE_PRICE, amount);

        // then
        assertEquals(expectedDiscount, actualDiscount);
    }

    static Stream<Arguments> shouldApplyStaticPercentageDiscountRegardlessOfAmount() {
        return Stream.of(
                Arguments.of(-100, BASE_PRICE.multiply(DISCOUNT_15)),
                Arguments.of(0, BASE_PRICE.multiply(DISCOUNT_15)),
                Arguments.of(1, BASE_PRICE.multiply(DISCOUNT_15)),
                Arguments.of(99, BASE_PRICE.multiply(DISCOUNT_15))
        );
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