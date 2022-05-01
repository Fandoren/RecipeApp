package com.surmin.recipe.service;

import com.surmin.recipe.mapper.TagMapper;
import com.surmin.recipe.model.Tag;
import com.surmin.recipe.model.TagDto;
import com.surmin.recipe.repository.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagService extends CrudService<TagDto, Tag, TagRepository>{

    public TagService(TagRepository tagRepository) {
        super(TagMapper.INSTANCE, tagRepository);
    }
}
