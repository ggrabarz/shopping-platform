package pl.inpost.shopping.core.port.out;

import pl.inpost.shopping.domain.Product;

import java.util.UUID;

public interface ProductDataAccess {
    Product findById(final UUID productId);
}
