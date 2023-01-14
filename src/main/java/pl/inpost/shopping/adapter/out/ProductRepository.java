package pl.inpost.shopping.adapter.out;

import org.springframework.stereotype.Component;
import pl.inpost.shopping.core.port.out.ProductDataAccess;
import pl.inpost.shopping.domain.Product;
import pl.inpost.shopping.domain.exception.ProductNotFound;

import java.util.UUID;

@Component
public class ProductRepository implements ProductDataAccess {

    private final ProductMongoRepository repository;

    public ProductRepository(final ProductMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product findById(final UUID productId) {
        ProductEty productEty = repository.findById(productId)
                .orElseThrow(() -> new ProductNotFound(productId));

        return productEty.toDomain();
    }
}
