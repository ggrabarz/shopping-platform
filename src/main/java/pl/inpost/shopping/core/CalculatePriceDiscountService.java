package pl.inpost.shopping.core;

import org.springframework.stereotype.Component;
import pl.inpost.shopping.core.policy.DiscountPolicy;
import pl.inpost.shopping.core.port.in.CalculatePriceDiscountUseCase;
import pl.inpost.shopping.core.port.out.ProductDataAccess;
import pl.inpost.shopping.domain.PriceDiscount;
import pl.inpost.shopping.domain.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Component
class CalculatePriceDiscountService implements CalculatePriceDiscountUseCase {

    private final List<DiscountPolicy> discountPolicies;
    private final ProductDataAccess productDataAccess;

    CalculatePriceDiscountService(
            final List<DiscountPolicy> discountPolicies,
            final ProductDataAccess productDataAccess
    ) {
        this.discountPolicies = discountPolicies;
        this.productDataAccess = productDataAccess;
    }

    @Override
    public PriceDiscount getPriceDiscount(final UUID productId, final Integer amount) {
        Product product = productDataAccess.findById(productId);
        BigDecimal basePrice = product.price();
        BigDecimal discount = getCumulativeDiscountUpToBasePrice(basePrice, amount);
        BigDecimal priceDiscounted = basePrice.subtract(discount).setScale(2, RoundingMode.HALF_UP);

        return new PriceDiscount(
                product.productId(),
                product.name(),
                amount,
                basePrice,
                priceDiscounted
        );
    }

    private BigDecimal getCumulativeDiscountUpToBasePrice(final BigDecimal basePrice, final Integer amount) {
        return discountPolicies.stream()
                .map(policy -> policy.calculateDiscount(basePrice, amount))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .min(basePrice);
    }
}
