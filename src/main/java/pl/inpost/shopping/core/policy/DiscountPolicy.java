package pl.inpost.shopping.core.policy;

import java.math.BigDecimal;

public interface DiscountPolicy {
    BigDecimal calculateDiscount(final BigDecimal basePrice, final Integer amount);

    default boolean isPriceNegative(final BigDecimal basePrice) {
        return basePrice.compareTo(BigDecimal.ZERO) < 0;
    }
}
