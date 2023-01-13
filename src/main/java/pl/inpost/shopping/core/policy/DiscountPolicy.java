package pl.inpost.shopping.core.policy;

import java.math.BigDecimal;

public interface DiscountPolicy {
    BigDecimal calculateDiscount(BigDecimal basePrice, Integer amount);

    default boolean isPriceNegative(BigDecimal basePrice) {
        return basePrice.compareTo(BigDecimal.ZERO) < 0;
    }
}
