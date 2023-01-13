package pl.inpost.shopping.core.policy;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@ConditionalOnProperty(value = "policy.amount-based.enabled", havingValue = "true")
class AmountBasedDiscountPolicy implements DiscountPolicy {

    private final AmountBasedDiscountPolicyProperties properties;

    AmountBasedDiscountPolicy(AmountBasedDiscountPolicyProperties properties) {
        this.properties = properties;
    }

    @Override
    public BigDecimal calculateDiscount(BigDecimal basePrice, Integer amount) {
        BigDecimal discount = BigDecimal.ZERO;
        return basePrice.multiply(discount);
    }
}
