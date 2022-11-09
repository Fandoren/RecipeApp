package com.surmin.recipe.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.surmin.recipe.model.TagDto;
import com.surmin.recipe.service.TagService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@TestPropertySource(properties = {"spring.cloud.config.discovery.enabled=false", "spring.cloud.config.enabled=false",
        "eureka.client.enabled=false"})
@WebMvcTest(TagController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TagControllerTest {

    private static final String TAG_PATH = "/api/v1/tags";
    private static final Logger LOGGER = LogManager.getLogger(TagControllerTest.class);

    @MockBean
    private TagService tagService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public void setUp() {
        TagDto tagDto = new TagDto();
        tagDto.setName("Name");
        tagDto.setDescription("Description");
        tagService.save(tagDto);
    }

    @Test
    public void getTagsShouldReturnList() throws Exception {
        LOGGER.info("GET URL {}", TAG_PATH);

        mockMvc.perform(get(TAG_PATH))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isArray());

    }

    @AfterAll
    public void drop() {
        //tagService.getAll().forEach((dto) ->tagService.delete(dto));
    }
}
