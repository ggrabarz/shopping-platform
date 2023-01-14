package pl.inpost.shopping.adapter.in;

import pl.inpost.shopping.domain.PriceDiscount;

import java.math.BigDecimal;
import java.util.UUID;

public record PriceDiscountDto(
        UUID productId,
        String name,
        Integer amount,
        BigDecimal priceBase,
        BigDecimal priceDiscounted
) {
    PriceDiscountDto(PriceDiscount priceDiscount) {
        this(
                priceDiscount.productId(),
                priceDiscount.name(),
                priceDiscount.amount(),
                priceDiscount.priceBase(),
                priceDiscount.priceDiscounted()
        );
    }
}
