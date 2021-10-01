package com.surmin.recipe.repository;

import com.surmin.recipe.model.Recipe;
import java.util.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {

    public Recipe findByName(String dishName);
    public Collection<Recipe> findByCookingTimeBetween(int from, int to);

}
