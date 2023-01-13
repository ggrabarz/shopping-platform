package pl.inpost.shopping.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record PriceDiscount(
        UUID productId,
        Integer amount,
        BigDecimal priceBase,
        BigDecimal priceDiscounted
) {
}
