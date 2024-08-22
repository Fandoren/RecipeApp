package com.surmin.recipe.service;

import com.surmin.recipe.exception.EntityNotFoundException;
import com.surmin.recipe.model.TagDto;
import com.surmin.recipe.repository.TagRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TagServiceTest {

    @Mock
    private TagRepository tagRepository;
    @InjectMocks
    private TagService tagService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveThrowsIllegalArgumentExceptionIfEntityIdSpecified() {
        TagDto tagDto = new TagDto();
        tagDto.setEntityId("SomeEntityId");
        tagDto.setName("SomeTag");
        tagDto.setDescription("Some long description");

        Assertions.assertThrows(IllegalArgumentException.class, () -> tagService.save(tagDto));
    }

    @Test
    public void saveThrowsEntityNotFoundExceptionIfTagDtoIsNull() {
        TagDto tagDto = null;

        Assertions.assertThrows(EntityNotFoundException.class, () -> tagService.save(tagDto));
    }
}
