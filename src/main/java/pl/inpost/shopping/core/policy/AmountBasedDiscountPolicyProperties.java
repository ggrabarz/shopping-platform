package pl.inpost.shopping.core.policy;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;

@ConfigurationProperties("policy.amount-based")
@Validated
public class AmountBasedDiscountPolicyProperties {

    @Valid
    private final Map<Integer, @Min(0) @Max(100) BigDecimal> discounts;

    @ConstructorBinding
    AmountBasedDiscountPolicyProperties(final SortedMap<Integer, BigDecimal> discounts) {
        if (!discounts.containsKey(0)) {
            discounts.put(0, BigDecimal.ZERO);
        }
        this.discounts = discounts;
    }

    public Map<Integer, BigDecimal> getDiscounts() {
        return Collections.unmodifiableMap(discounts);
    }
}
