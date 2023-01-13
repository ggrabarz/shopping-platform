package pl.inpost.shopping.core.port.in;

import pl.inpost.shopping.domain.PriceDiscount;

import java.util.UUID;

public interface CalculatePriceDiscountUseCase {
    PriceDiscount getPriceDiscount(final UUID productId, final Integer amount);
}
