package com.surmin.recipe.controller;

import com.surmin.recipe.model.Tag;
import com.surmin.recipe.model.TagDto;
import com.surmin.recipe.service.TagService;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
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
@RequestMapping("/api/v1/tags")
public class TagController {

    private final TagService tagService;

    TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("{entityId}")
    public TagDto get(@PathVariable("entityId") String entityId){
        return tagService.get(entityId);
    }

    @GetMapping
    public Collection<TagDto> getAll(){
        return tagService.getAll();
    }

    @GetMapping("page/{pageId}")
    public Page<TagDto> getPage(@PathVariable("pageId") Integer pageId) {
        return tagService.getPage(pageId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagDto save(@RequestBody TagDto tagDto) {
        return tagService.save(tagDto);
    }

    @GetMapping("/{entityId}/image")
    public ResponseEntity<byte[]> getImage(
            @PathVariable("entityId") String id
    ) {
        Tag tag = tagService.getTag(id);
        byte[] imageAsByteArray = tag.getImageAsByteArray();
        return ResponseEntity.status(HttpStatus.OK).contentLength(imageAsByteArray.length).body(imageAsByteArray);
    }

    @PostMapping("/{entityId}/image")
    public ResponseEntity<Void> uploadImage(
            @PathVariable("entityId") String id,
            @RequestParam(value = "image") MultipartFile image
    ) throws IOException {
        byte[] imageAsByteArray = image.getBytes();
        tagService.saveImage(id, imageAsByteArray);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(location).build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagDto update(@RequestBody TagDto tagDto) {
        return tagService.update(tagDto);
    }

    @DeleteMapping("/{entityId}")
    public void delete(@PathVariable("entityId") String entityId) {
        TagDto tagDto = tagService.get(entityId);
        tagService.delete(tagDto);
    }

}
