package com.surmin.recipe.controller;

import com.surmin.recipe.model.Recipe;
import com.surmin.recipe.model.RecipeDto;
import com.surmin.recipe.service.RecipeService;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @PostMapping("page/{pageId}")
    public Page<RecipeDto> getPageFilterByTagAndProductIds(
            @PathVariable("pageId") Integer pageId,
            @RequestBody Map<String,Collection<String>> body) {
        return recipeService.getPageFilterByTagAndProductIds(pageId, body.get("tagIds"), body.get("productIds"));
    }

    @GetMapping("products/{entityId}")
    public Collection<RecipeDto> getByProductIdIn(@PathVariable("entityId") String entityId) {
        return recipeService.getByProductId(entityId);
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

    @GetMapping("/{entityId}/image")
    public ResponseEntity<byte[]> getImage(
            @PathVariable("entityId") String id
    ) {
        Recipe recipe = recipeService.getRecipe(id);
        byte[] imageAsByteArray = recipe.getImageAsByteArray();
        return ResponseEntity.status(HttpStatus.OK).contentLength(imageAsByteArray.length).body(imageAsByteArray);
    }

    @PostMapping("/{entityId}/image")
    public ResponseEntity<Void> uploadImage(
            @PathVariable("entityId") String id,
            @RequestParam(value = "image") MultipartFile image
    ) throws IOException {
        byte[] imageAsByteArray = image.getBytes();
        recipeService.saveImage(id, imageAsByteArray);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(location).build();
    }

}
