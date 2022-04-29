package com.surmin.recipe.model;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "steps")
public class Step extends DomainObject{

    @Field
    private String title;
    @Field
    private String description;
    @Field
    private List<byte[]> imagesAsByteArray;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<byte[]> getImagesAsByteArray() {
        return imagesAsByteArray;
    }

    public void setImagesAsByteArray(List<byte[]> imagesAsByteArray) {
        this.imagesAsByteArray = imagesAsByteArray;
    }
}
