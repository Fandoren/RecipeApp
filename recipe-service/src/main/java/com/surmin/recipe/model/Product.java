package com.surmin.recipe.model;

import org.springframework.data.annotation.Id;

public class Product {

    @Id
    private String id;

    private String name;
    private Integer calorie;
    private String type;

    public Product(String name, Integer calorie, String type) {
        this.name = name;
        this.calorie = calorie;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalorie() {
        return calorie;
    }

    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
