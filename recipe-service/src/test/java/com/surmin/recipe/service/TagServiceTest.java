package com.surmin.recipe.service;

import com.surmin.recipe.exception.EntityNotFoundException;
import com.surmin.recipe.model.TagDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"spring.cloud.config.discovery.enabled=false", "spring.cloud.config.enabled=false",
        "eureka.client.enabled=false"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TagServiceTest {

    @Autowired
    private TagService tagService;

    @BeforeAll
    public void setUp() {
        TagDto tagDto = new TagDto();
        tagDto.setName("Fruit");
        tagDto.setDescription("Some description of fruit tag");
        tagService.save(tagDto);

        tagDto = new TagDto();
        tagDto.setName("Meat");
        tagDto.setDescription("Some description of meat tag");
        tagService.save(tagDto);
    }

    @AfterAll
    public void tearDown() {
        //tagService.getAll().forEach((dto) ->tagService.delete(dto));
    }

    @Test
    public void countTags() {
        Assertions.assertEquals(2, tagService.getAll().size());
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

    @Test
    public void getPageCount() {
        Page<TagDto> page = tagService.getPage(0);

        Assertions.assertEquals(1, page.getTotalPages());
    }
}
