package com.surmin.recipe.controller;

import com.surmin.recipe.model.Product;
import com.surmin.recipe.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{entityId}")
    public String get(@PathVariable("entityId") String entityId) throws JsonProcessingException {
       return productService.get(entityId);
    }

    @GetMapping("/product")
    public String getAll() throws JsonProcessingException {
        return productService.getAll();
    }

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public Product insert(@RequestBody Product product) {
        return productService.insert(product);
    }

    @PutMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public Product edit(@RequestBody Product product) {
        return productService.edit(product);
    }

    @DeleteMapping("/product/{entityId}")
    public void delete(@PathVariable("entityId") String entityId) {
        productService.delete(entityId);
    }

}
