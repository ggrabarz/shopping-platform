package pl.inpost.shopping.adapter.out;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface ProductMongoRepository extends MongoRepository<ProductEty, UUID> {
}
