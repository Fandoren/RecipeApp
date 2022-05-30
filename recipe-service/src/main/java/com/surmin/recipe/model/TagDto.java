package com.surmin.recipe.model;

public class TagDto extends DtoObject{

    private String name;
    private String description;
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
