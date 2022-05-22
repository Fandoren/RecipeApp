package com.surmin.recipe.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "tags")
public class Tag extends DomainObject{

    @Field
    private String name;
    @Field
    private String description;
    @Field
    private byte[] imageAsByteArray;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImageAsByteArray() {
        return imageAsByteArray;
    }

    public void setImageAsByteArray(byte[] imageAsByteArray) {
        this.imageAsByteArray = imageAsByteArray;
    }
}
