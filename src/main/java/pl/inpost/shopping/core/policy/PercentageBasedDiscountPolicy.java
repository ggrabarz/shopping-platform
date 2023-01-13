package pl.inpost.shopping.core.policy;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import pl.inpost.shopping.domain.exception.NegativePriceException;

import java.math.BigDecimal;

@Component
@ConditionalOnProperty(value = "policy.percentage-based.enabled", havingValue = "true")
class PercentageBasedDiscountPolicy implements DiscountPolicy {

    private final PercentageBasedDiscountPolicyProperties properties;

    public PercentageBasedDiscountPolicy(PercentageBasedDiscountPolicyProperties properties) {
        this.properties = properties;
    }

    @Override
    public BigDecimal calculateDiscount(BigDecimal basePrice, Integer amount) {
        if (isPriceNegative(basePrice)) {
            throw new NegativePriceException();
        }

        BigDecimal discount = properties.getDiscount();
        return basePrice.multiply(discount);
    }
}
