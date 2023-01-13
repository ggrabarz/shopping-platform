package pl.inpost.shopping.core;

import org.springframework.stereotype.Component;
import pl.inpost.shopping.core.port.in.CalculatePriceDiscountUseCase;
import pl.inpost.shopping.core.port.out.ProductDataAccess;
import pl.inpost.shopping.domain.PriceDiscount;
import pl.inpost.shopping.domain.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Component
class CalculatePriceDiscountService implements CalculatePriceDiscountUseCase {

    private final ProductDataAccess productDataAccess;

    CalculatePriceDiscountService(final ProductDataAccess productDataAccess) {
        this.productDataAccess = productDataAccess;
    }

    @Override
    public PriceDiscount getPriceDiscount(final UUID productId, final Integer amount) {
        Product product = productDataAccess.findById(productId);
        BigDecimal discount = BigDecimal.valueOf(0.33);
        BigDecimal priceDiscounted = product.price().multiply(discount).setScale(2, RoundingMode.HALF_UP);

        return new PriceDiscount(
                product.productId(),
                amount,
                product.price(),
                priceDiscounted
        );
    }
}
