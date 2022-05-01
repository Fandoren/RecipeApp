package com.surmin.recipe.service;

import com.surmin.recipe.mapper.RecipeMapper;
import com.surmin.recipe.model.Recipe;
import com.surmin.recipe.model.RecipeDto;
import com.surmin.recipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeService extends CrudService<RecipeDto, Recipe, RecipeRepository> {

    public RecipeService(RecipeRepository recipeRepository) {
        super(RecipeMapper.INSTANCE, recipeRepository);
    }
}
