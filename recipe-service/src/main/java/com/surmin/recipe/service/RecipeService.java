package com.surmin.recipe.service;

import com.surmin.recipe.mapper.RecipeMapper;
import com.surmin.recipe.model.Recipe;
import com.surmin.recipe.model.RecipeDto;
import com.surmin.recipe.repository.RecipeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RecipeService extends CrudService<RecipeDto, Recipe, RecipeRepository> {

    public RecipeService(RecipeRepository recipeRepository) {
        super(RecipeMapper.INSTANCE, recipeRepository);
    }

    public Page<RecipeDto> getPage(Integer pageId) {
        Pageable pageable = PageRequest.of(pageId, 8);
        return getAll(pageable);
    }
}
