package pl.inpost.shopping.core.policy;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import pl.inpost.shopping.domain.exception.NegativeAmountException;
import pl.inpost.shopping.domain.exception.NegativePriceException;

import java.math.BigDecimal;
import java.util.Map.Entry;

@Component
@ConditionalOnProperty(value = "policy.amount-based.enabled", havingValue = "true")
class AmountBasedDiscountPolicy implements DiscountPolicy {

    private final AmountBasedDiscountPolicyProperties properties;

    AmountBasedDiscountPolicy(final AmountBasedDiscountPolicyProperties properties) {
        this.properties = properties;
    }

    @Override
    public BigDecimal calculateDiscount(final BigDecimal basePrice, final Integer amount) {
        if (isPriceNegative(basePrice)) {
            throw new NegativePriceException();
        }

        if (amount < 0) {
            throw new NegativeAmountException();
        }

        BigDecimal discount = findDiscountPercentage(amount);
        return basePrice.multiply(discount);
    }

    private BigDecimal findDiscountPercentage(final Integer amount) {
        return properties.getDiscounts()
                .entrySet()
                .stream()
                .filter(e -> e.getKey() <= amount)
                .max(Entry.comparingByKey())
                .map(Entry::getValue)
                .orElse(BigDecimal.ZERO);
    }
}
