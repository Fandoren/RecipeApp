package com.surmin.recipe.controller;

import com.surmin.recipe.model.Recipe;
import com.surmin.recipe.service.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {

    private final RecipeService recipeService;

    RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{entityId}")
    public String get(@PathVariable("entityId") String entityId) throws JsonProcessingException {
        return recipeService.get(entityId);
    }

    @GetMapping("/recipe")
    public String getAll() throws JsonProcessingException {
        return recipeService.getAll();
    }

    @PostMapping("/recipe")
    @ResponseStatus(HttpStatus.CREATED)
    public Recipe insert(@RequestBody Recipe recipe) {
        return recipeService.insert(recipe);
    }

    @PutMapping("/recipe")
    @ResponseStatus(HttpStatus.CREATED)
    public Recipe edit(@RequestBody Recipe recipe) {
        return recipeService.edit(recipe);
    }

    @DeleteMapping("/recipe/{entityId}")
    public void delete(@PathVariable("entityId") String entityId) {
        recipeService.delete(entityId);
    }

}
