package com.surmin.recipe.service;

import com.surmin.recipe.model.Recipe;
import com.surmin.recipe.repository.RecipeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public String get(String id) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(recipeRepository.findById(id).orElse(null));
    }

    @Override
    public String getAll() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(recipeRepository.findAll());
    }

    @Override
    public Recipe edit(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe insert(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public void delete(String id) {
        final Recipe recipe = recipeRepository.findById(id).orElse(null);
        if(recipe == null) {
            throw new NullPointerException();
        }
        recipeRepository.delete(recipe);
    }
}
