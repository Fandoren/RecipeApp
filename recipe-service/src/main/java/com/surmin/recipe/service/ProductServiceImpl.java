package com.surmin.recipe.service;

import com.surmin.recipe.model.Product;
import com.surmin.recipe.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String get(String id) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(productRepository.findById(id).orElse(null));
    }

    @Override
    public String getAll() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(productRepository.findAll());
    }

    @Override
    public Product edit(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(String id) {
        final Product product = productRepository.findById(id).orElse(null);
        if(product == null) {
            throw new NullPointerException();
        }
        productRepository.delete(product);
    }

    @Override
    public Product insert(Product product) {
        return productRepository.save(product);
    }
}
