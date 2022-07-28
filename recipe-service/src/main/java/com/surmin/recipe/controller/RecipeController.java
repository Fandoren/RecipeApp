package com.surmin.recipe.controller;

import com.surmin.recipe.model.RecipeDto;
import com.surmin.recipe.service.RecipeService;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("{entityId}")
    public RecipeDto get(@PathVariable("entityId") String entityId){
        return recipeService.get(entityId);
    }

    @GetMapping
    public Collection<RecipeDto> getAll(){
        return recipeService.getAll();
    }

    @GetMapping("page/{pageId}")
    public Page<RecipeDto> getPage(@PathVariable("pageId") Integer pageId) {
        return recipeService.getPage(pageId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeDto save(@RequestBody RecipeDto recipeDto) {
        return recipeService.save(recipeDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeDto edit(@RequestBody RecipeDto recipeDto) {
        return recipeService.update(recipeDto);
    }

    @DeleteMapping("/{entityId}")
    public void delete(@PathVariable("entityId") String entityId) {
        RecipeDto recipeDto = recipeService.get(entityId);
        recipeService.delete(recipeDto);
    }

}
