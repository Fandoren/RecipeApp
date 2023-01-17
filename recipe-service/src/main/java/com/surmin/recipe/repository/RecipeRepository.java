package com.surmin.recipe.repository;

import com.surmin.recipe.model.Recipe;
import java.util.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {
    @Query("{'ingredients.productId': ?0}")
    Collection<Recipe> findByProductIdIn(Collection<String> entityId);
}
