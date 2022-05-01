package com.surmin.recipe.controller;

import com.surmin.recipe.model.TagDto;
import com.surmin.recipe.service.TagService;
import java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class TagController {

    private final TagService tagService;

    TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/tag/{entityId}")
    public TagDto get(@PathVariable("entityId") String entityId){
        return tagService.get(entityId);
    }

    @GetMapping("/tag")
    public Collection<TagDto> getAll(){
        return tagService.getAll();
    }

    @PostMapping("/tag")
    @ResponseStatus(HttpStatus.CREATED)
    public TagDto save(@RequestBody TagDto tagDto) {
        return tagService.save(tagDto);
    }

    @PutMapping("/tag")
    @ResponseStatus(HttpStatus.CREATED)
    public TagDto update(@RequestBody TagDto tagDto) {
        return tagService.update(tagDto);
    }

    @DeleteMapping("/tag/{entityId}")
    public void delete(@PathVariable("entityId") String entityId) {
        TagDto tagDto = tagService.get(entityId);
        tagService.delete(tagDto);
    }

}
