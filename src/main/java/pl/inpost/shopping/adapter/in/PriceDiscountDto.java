package pl.inpost.shopping.adapter.in;

import pl.inpost.shopping.domain.PriceDiscount;

import java.math.BigDecimal;
import java.util.UUID;

public record PriceDiscountDto(
        UUID productId,
        Integer amount,
        BigDecimal priceBase,
        BigDecimal priceDiscounted
) {
    PriceDiscountDto(PriceDiscount priceDiscount) {
        this(
                priceDiscount.productId(),
                priceDiscount.amount(),
                priceDiscount.priceBase(),
                priceDiscount.priceDiscounted()
        );
    }
}
