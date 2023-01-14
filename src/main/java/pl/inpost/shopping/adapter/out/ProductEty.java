package pl.inpost.shopping.adapter.out;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.inpost.shopping.domain.Product;

import java.math.BigDecimal;
import java.util.UUID;

@Document("products")
record ProductEty(
        @Id
        UUID productId,
        String name,
        BigDecimal price
) {
    Product toDomain() {
        return new Product(productId, name, price);
    }
}
