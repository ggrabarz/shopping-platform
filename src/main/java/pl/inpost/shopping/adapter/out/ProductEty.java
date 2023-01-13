package pl.inpost.shopping.adapter.out;

import pl.inpost.shopping.domain.Product;

import java.math.BigDecimal;
import java.util.UUID;

record ProductEty(UUID productId, String name, BigDecimal price) {
    Product toDomain() {
        return new Product(productId, name, price);
    }
}
