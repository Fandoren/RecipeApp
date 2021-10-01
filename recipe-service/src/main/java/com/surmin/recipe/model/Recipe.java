package com.surmin.recipe.model;

import java.util.List;
import org.springframework.data.annotation.Id;

public class Recipe {

    @Id
    private String id;

    private String name;
    private Integer cookingTime;
    private List<Product> productList;
    private String desctiprion;
    private String author;
    private String tag;

    public Recipe(String name, Integer cookingTime, List<Product> productList,
            String desctiprion, String author, String tag) {
        this.name = name;
        this.cookingTime = cookingTime;
        this.productList = productList;
        this.desctiprion = desctiprion;
        this.author = author;
        this.tag = tag;
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

    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getDesctiprion() {
        return desctiprion;
    }

    public void setDesctiprion(String desctiprion) {
        this.desctiprion = desctiprion;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
