package pl.inpost.shopping.adapter.in;


import jakarta.validation.constraints.Positive;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;


@RestController
@Validated
public class CalculatePriceDiscountEndpoint {

    @GetMapping(
            path = "/calculate-price-discount",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    PriceDiscountDto calculatePriceDiscount(
            @RequestParam UUID productId,
            @RequestParam @Positive Integer amount
    ) {
        return new PriceDiscountDto(
                productId,
                amount,
                BigDecimal.valueOf(399.00),
                BigDecimal.valueOf(235.15)
        );
    }
}
