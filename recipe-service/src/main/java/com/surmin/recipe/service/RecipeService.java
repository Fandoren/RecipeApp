package com.surmin.recipe.service;

import com.surmin.recipe.exception.EntityNotFoundException;
import com.surmin.recipe.mapper.RecipeMapper;
import com.surmin.recipe.model.Recipe;
import com.surmin.recipe.model.RecipeDto;
import com.surmin.recipe.repository.RecipeRepository;
import java.io.IOException;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RecipeService extends CrudService<RecipeDto, Recipe, RecipeRepository> {

    private static final String RECIPE_WAS_NOT_FOUND = "Recipe entity wasn't found";
    private static final String IMAGE_IS_EMPTY = "Image can't be empty";
    private static final String IMAGE_IS_OVERSIZED = "Image oversized, it should not exceed 1MB";

    public static final int IMAGE_BYTES_LIMIT = 1 << 20;

    public RecipeService(RecipeRepository recipeRepository) {
        super(RecipeMapper.INSTANCE, recipeRepository);
    }

    public Page<RecipeDto> getPage(Integer pageId) {
        Pageable pageable = PageRequest.of(pageId, 8);
        return getAll(pageable);
    }

    public Recipe getRecipe(String entityId) {
        Optional<Recipe> recipeOptional = getMongoRepository().findById(entityId);
        if (recipeOptional.isEmpty()) {
            throw new EntityNotFoundException(RECIPE_WAS_NOT_FOUND);
        }
        return recipeOptional.get();
    }

    public void saveImage(String entityId, byte[] imageAsByteArray) throws IOException {
        if (imageAsByteArray == null || imageAsByteArray.length <= 0) {
            throw new IllegalArgumentException(IMAGE_IS_EMPTY);
        }
        if (imageAsByteArray.length > IMAGE_BYTES_LIMIT) {
            throw new IllegalArgumentException(IMAGE_IS_OVERSIZED);
        }
        Optional<Recipe> recipeOptional = getMongoRepository().findById(entityId);
        if (recipeOptional.isEmpty()) {
            throw new EntityNotFoundException(RECIPE_WAS_NOT_FOUND);
        }
        Recipe recipe = recipeOptional.get();
        recipe.setImageAsByteArray(imageAsByteArray);
        getMongoRepository().save(recipe);
    }
}
