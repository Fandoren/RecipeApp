package com.surmin.recipe.controller;

import com.surmin.recipe.model.ProductDto;
import com.surmin.recipe.service.ProductService;
import java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{entityId}")
    public ProductDto get(@PathVariable("entityId") String entityId){
       return productService.get(entityId);
    }

    @GetMapping
    public Collection<ProductDto> getAll(){
        return productService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto save(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto update(@RequestBody ProductDto productDto) {
        return productService.update(productDto);
    }

    @DeleteMapping("/{entityId}")
    public void delete(@PathVariable("entityId") String entityId) {
        ProductDto productDto = productService.get(entityId);
        productService.delete(productDto);
    }

}
