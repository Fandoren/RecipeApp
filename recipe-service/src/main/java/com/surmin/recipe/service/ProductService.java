package com.surmin.recipe.service;

import com.surmin.recipe.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ProductService {

    public String get(String id) throws JsonProcessingException;
    public String getAll() throws JsonProcessingException;
    public Product edit(Product product);
    public Product insert(Product product);
    public void delete(String id);

}
