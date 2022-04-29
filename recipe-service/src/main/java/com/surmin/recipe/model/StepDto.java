package com.surmin.recipe.model;

import java.util.List;

public class StepDto extends DtoObject{

    private String title;
    private String description;
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
