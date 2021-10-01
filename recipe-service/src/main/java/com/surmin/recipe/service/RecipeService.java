package com.surmin.recipe.service;

import com.surmin.recipe.model.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RecipeService {
    public String get(String id) throws JsonProcessingException;
    public String getAll() throws JsonProcessingException;
    public Recipe edit(Recipe recipe);
    public Recipe insert(Recipe recipe);
    public void delete(String id);
}
