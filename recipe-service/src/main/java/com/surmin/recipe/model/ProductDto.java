package com.surmin.recipe.model;

import java.util.Set;

public class ProductDto extends DtoObject{

    private String name;
    private String description;
    private Set<String> tagIds;
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

    public Set<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(Set<String> tagIds) {
        this.tagIds = tagIds;
    }

    public byte[] getImageAsByteArray() {
        return imageAsByteArray;
    }

    public void setImageAsByteArray(byte[] imageAsByteArray) {
        this.imageAsByteArray = imageAsByteArray;
    }
}
