package pl.inpost.shopping.adapter.in;

import java.math.BigDecimal;
import java.util.UUID;

public record PriceDiscountDto(
        UUID productId,
        Integer amount,
        BigDecimal priceBase,
        BigDecimal priceDiscounted
) {
}
