package com.surmin.recipe.service;

import com.surmin.recipe.mapper.TagMapper;
import com.surmin.recipe.model.Tag;
import com.surmin.recipe.model.TagDto;
import com.surmin.recipe.repository.TagRepository;
import java.io.IOException;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TagService extends CrudService<TagDto, Tag, TagRepository>{

    public TagService(TagRepository tagRepository) {
        super(TagMapper.INSTANCE, tagRepository);
    }

    public static final int IMAGE_BYTES_LIMIT = 1 << 20;

    public Page<TagDto> getPage(Integer pageId) {
        Pageable pageable = PageRequest.of(pageId, 8);
        return getAll(pageable);
    }

    public Tag getTag(String entityId) {
        Optional<Tag> tagOptional = getMongoRepository().findById(entityId);
        if (tagOptional.isEmpty()) {
            throw new IllegalArgumentException("TAG_WAS_NOT_FOUND");
        }
        return tagOptional.get();
    }

    public void saveImage(String entityId, byte[] imageAsByteArray) throws IOException {
        if (imageAsByteArray == null || imageAsByteArray.length <= 0) {
            throw new IllegalArgumentException("IMAGE_IS_EMPTY");
        }
        if (imageAsByteArray.length > IMAGE_BYTES_LIMIT) {
            throw new IllegalArgumentException("IMAGE_IS_OVERSIZED");
        }
        Optional<Tag> tagOptional = getMongoRepository().findById(entityId);
        if (tagOptional.isEmpty()) {
            throw new IllegalArgumentException("TAG_WAS_NOT_FOUND");
        }
        Tag tag = tagOptional.get();
        tag.setImageAsByteArray(imageAsByteArray);
        getMongoRepository().save(tag);
    }
}
