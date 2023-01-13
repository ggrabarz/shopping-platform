package pl.inpost.shopping.core;

import org.springframework.stereotype.Component;
import pl.inpost.shopping.core.port.in.CalculatePriceDiscountUseCase;
import pl.inpost.shopping.core.port.out.ProductDataAccess;
import pl.inpost.shopping.domain.PriceDiscount;

import java.util.UUID;

@Component
class CalculatePriceDiscountService implements CalculatePriceDiscountUseCase {

    private ProductDataAccess productDataAccess;

    CalculatePriceDiscountService(final ProductDataAccess productDataAccess) {
        this.productDataAccess = productDataAccess;
    }

    @Override
    public PriceDiscount getPriceDiscount(final UUID productId, final Integer amount) {
        return null;
    }
}
