package com.surmin.recipe.repository;

import com.surmin.recipe.model.Product;
import com.surmin.recipe.model.ProductDto;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Collection<Product> findByTagIdsIn(String entityId);
    Page<ProductDto> findByTagIdsIn(Collection<String> tagIds, Pageable pageable);
}
