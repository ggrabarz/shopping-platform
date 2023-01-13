package pl.inpost.shopping.core.policy;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@ConfigurationProperties("policy.percentage-based")
@Validated
public class PercentageBasedDiscountPolicyProperties {

    @Min(0)
    @Max(100)
    private final BigDecimal discount;

    @ConstructorBinding
    PercentageBasedDiscountPolicyProperties(final BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}
