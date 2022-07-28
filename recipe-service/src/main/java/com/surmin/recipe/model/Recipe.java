package com.surmin.recipe.model;

import java.time.LocalTime;
import java.util.Collection;
import java.util.Set;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "recipes")
public class Recipe extends DomainObject{

    @Field
    private String name;
    @Field
    private byte[] imageAsByteArray;
    @Field
    private Set<String> ingredientIds;
    @Field
    private LocalTime cookingTime;
    @Field
    private LocalTime prepTime;
    @Field
    private double calories;
    @Field
    private String description;
    @Field
    private Collection<Step> steps;
    @Field
    private Set<String> tagIds;
    @Field
    private String author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImageAsByteArray() {
        return imageAsByteArray;
    }

    public void setImageAsByteArray(byte[] imageAsByteArray) {
        this.imageAsByteArray = imageAsByteArray;
    }

    public Set<String> getIngredientIds() {
        return ingredientIds;
    }

    public void setIngredientIds(Set<String> ingredientIds) {
        this.ingredientIds = ingredientIds;
    }

    public LocalTime getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(LocalTime cookingTime) {
        this.cookingTime = cookingTime;
    }

    public LocalTime getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(LocalTime prepTime) {
        this.prepTime = prepTime;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Step> getSteps() {
        return steps;
    }

    public void setSteps(Collection<Step> steps) {
        this.steps = steps;
    }

    public Set<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(Set<String> tagIds) {
        this.tagIds = tagIds;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
