package com.surmin.recipe.repository;

import com.surmin.recipe.model.Product;
import java.util.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Collection<Product> findByTagIdsIn(String entityId);
}
