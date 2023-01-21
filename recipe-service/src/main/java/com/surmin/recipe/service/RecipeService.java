package com.surmin.recipe.service;

import com.surmin.recipe.exception.EntityNotFoundException;
import com.surmin.recipe.mapper.EntityToDtoMapper;
import com.surmin.recipe.mapper.RecipeMapper;
import com.surmin.recipe.model.Ingredient;
import com.surmin.recipe.model.Recipe;
import com.surmin.recipe.model.RecipeDto;
import com.surmin.recipe.repository.RecipeRepository;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class RecipeService extends CrudService<RecipeDto, Recipe, RecipeRepository> {

    private static final String RECIPE_WAS_NOT_FOUND = "Recipe entity wasn't found";
    private static final String IMAGE_IS_EMPTY = "Image can't be empty";
    private static final String IMAGE_IS_OVERSIZED = "Image oversized, it should not exceed 1MB";

    private final MongoTemplate mongoTemplate;

    public static final int IMAGE_BYTES_LIMIT = 1 << 20;

    public RecipeService(RecipeRepository recipeRepository, MongoTemplate mongoTemplate) {
        super(RecipeMapper.INSTANCE, recipeRepository);
        this.mongoTemplate = mongoTemplate;
    }

    public Page<RecipeDto> getPage(Integer pageId) {
        Pageable pageable = PageRequest.of(pageId, 8);
        return getAll(pageable);
    }

    public Page<RecipeDto> getPageFilterByTagAndProductIds(
            Integer pageId,
            Collection<String> tagIds,
            Collection<String> productIds
    ) {
        Pageable pageable = PageRequest.of(pageId, 8);
        Query query = new Query();
        if(tagIds.size() != 0) {
            query.addCriteria(Criteria.where("tagIds").in(tagIds));
        }
        if(productIds.size() != 0) {
            query.addCriteria(Criteria.where("ingredients.productId").in(productIds));
        }
        List<RecipeDto> list = mongoTemplate.find(query,Recipe.class).stream().map(getMapper()::entityToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(list, pageable, list.size());
    }

    public Recipe getRecipe(String entityId) {
        Optional<Recipe> recipeOptional = getMongoRepository().findById(entityId);
        if (recipeOptional.isEmpty()) {
            throw new EntityNotFoundException(RECIPE_WAS_NOT_FOUND);
        }
        return recipeOptional.get();
    }

    public Collection<RecipeDto> getByProductId(String entityId) {
        List<RecipeDto> recipes = new ArrayList<>();
        EntityToDtoMapper<Recipe, RecipeDto> mapper = getMapper();
        getMongoRepository().findByProductIdIn(entityId).forEach(recipe -> {
            recipes.add(mapper.entityToDto(recipe));
        });
        return recipes;
    }

    public Collection<RecipeDto> getByIngredientQuantitiesIn(Collection<Ingredient> ingredients) {
        Query query = new Query();
        for (Ingredient ingredient : ingredients) {
            query.addCriteria(Criteria.where("ingredients.productId").is(ingredient.getProductId())
                    .and("ingredients.weight").lte(ingredient.getWeight())
            );
        }
        EntityToDtoMapper<Recipe, RecipeDto> mapper = getMapper();
        Set<RecipeDto> recipes = new HashSet<>();
        mongoTemplate.find(query, Recipe.class).forEach(recipe -> recipes.add(mapper.entityToDto(recipe)));
        return recipes;
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
