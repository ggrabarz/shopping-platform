package pl.inpost.shopping.adapter.in;


import jakarta.validation.constraints.Positive;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.inpost.shopping.core.port.in.CalculatePriceDiscountUseCase;
import pl.inpost.shopping.domain.PriceDiscount;

import java.util.UUID;


@RestController
@Validated
public class CalculatePriceDiscountEndpoint {

    private final CalculatePriceDiscountUseCase calculatePriceDiscountUseCase;

    CalculatePriceDiscountEndpoint(
            CalculatePriceDiscountUseCase calculatePriceDiscountUseCase
    ) {
        this.calculatePriceDiscountUseCase = calculatePriceDiscountUseCase;
    }

    @GetMapping(
            path = "/calculate-price-discount",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    PriceDiscountDto calculatePriceDiscount(
            @RequestParam UUID productId,
            @RequestParam @Positive Integer amount
    ) {
        PriceDiscount priceDiscount = calculatePriceDiscountUseCase.getPriceDiscount(productId, amount);
        return new PriceDiscountDto(priceDiscount);
    }
}
