package com.surmin.recipe.service;

import com.surmin.recipe.exception.EntityNotFoundException;
import com.surmin.recipe.mapper.EntityToDtoMapper;
import com.surmin.recipe.mapper.TagMapper;
import com.surmin.recipe.model.Tag;
import com.surmin.recipe.model.TagDto;
import com.surmin.recipe.repository.TagRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TagService extends CrudService<TagDto, Tag, TagRepository>{

    private static final String TAG_WAS_NOT_FOUND = "Tag entity wasn't found";
    private static final String IMAGE_IS_EMPTY = "Image can't be empty";
    private static final String IMAGE_IS_OVERSIZED = "Image oversized, it should not exceed 1MB";

    public static final int IMAGE_BYTES_LIMIT = 1 << 20;

    public TagService(TagRepository tagRepository) {
        super(TagMapper.INSTANCE, tagRepository);
    }

    public Page<TagDto> getPage(Integer pageId) {
        Pageable pageable = PageRequest.of(pageId, 8);
        return getAll(pageable);
    }

    public Tag getTag(String entityId) {
        Optional<Tag> tagOptional = getMongoRepository().findById(entityId);
        if (tagOptional.isEmpty()) {
            throw new EntityNotFoundException(TAG_WAS_NOT_FOUND);
        }
        return tagOptional.get();
    }

    public Collection<TagDto> getTagsByIds(Collection<String> entityIds) {
        List<TagDto> tags = new ArrayList<>();
        EntityToDtoMapper<Tag, TagDto> mapper = getMapper();
        getMongoRepository().findAllById(entityIds).forEach(tag -> {
            tags.add(mapper.entityToDto(tag));
        });
        return tags;
    }

    public void saveImage(String entityId, byte[] imageAsByteArray) throws IOException {
        if (imageAsByteArray == null || imageAsByteArray.length <= 0) {
            throw new IllegalArgumentException(IMAGE_IS_EMPTY);
        }
        if (imageAsByteArray.length > IMAGE_BYTES_LIMIT) {
            throw new IllegalArgumentException(IMAGE_IS_OVERSIZED);
        }
        Optional<Tag> tagOptional = getMongoRepository().findById(entityId);
        if (tagOptional.isEmpty()) {
            throw new EntityNotFoundException(TAG_WAS_NOT_FOUND);
        }
        Tag tag = tagOptional.get();
        tag.setImageAsByteArray(imageAsByteArray);
        getMongoRepository().save(tag);
    }
}
