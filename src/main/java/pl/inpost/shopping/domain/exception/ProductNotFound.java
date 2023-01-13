package pl.inpost.shopping.domain.exception;

import java.util.UUID;

public final class ProductNotFound extends DomainException {
    public ProductNotFound(UUID productId) {
        super("Product does not exist [productId: " + productId + "]");
    }
}
