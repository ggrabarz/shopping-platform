package pl.inpost.shopping.adapter.out;

import org.springframework.stereotype.Component;
import pl.inpost.shopping.core.port.out.ProductDataAccess;
import pl.inpost.shopping.domain.Product;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Component
public class ProductRepository implements ProductDataAccess {

    private final Set<ProductEty> mockProducts = Set.of(
            new ProductEty(UUID.fromString("e737b48b-1fe2-476a-ba2e-85c44f036e86"), "Apple iPhone 14 Pro Max", new BigDecimal("6499.00")),
            new ProductEty(UUID.fromString("a8ef1604-79a0-4b0a-88af-3a131934398f"), "Samsung Galaxy S22 Ultra", new BigDecimal("5199.00")),
            new ProductEty(UUID.fromString("09716a47-ab35-4753-8f12-b52e0f9ceca2"), "ASUS Zenfone 9", new BigDecimal("3799.00")),
            new ProductEty(UUID.fromString("3c0f02b9-ac9f-4d1e-9039-17007f2aa606"), "HUAWEI P50 Pro", new BigDecimal("4999.00")),
            new ProductEty(UUID.fromString("5894f902-3f63-4aac-9ef2-40c8551e2ac3"), "Xiaomi 12T Pro", new BigDecimal("3099.00"))
    );

    @Override

    public Product findById(final UUID productId) {
        ProductEty productEty = mockProducts.stream()
                .filter(product -> product.productId().equals(productId))
                .findFirst()
                .get();
        return productEty.toDomain();
    }
}
