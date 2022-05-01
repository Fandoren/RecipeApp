package com.surmin.recipe.service;

import com.surmin.recipe.mapper.ProductMapper;
import com.surmin.recipe.model.Product;
import com.surmin.recipe.model.ProductDto;
import com.surmin.recipe.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends CrudService<ProductDto, Product, ProductRepository>{

    public ProductService(ProductRepository productRepository) {
        super(ProductMapper.INSTANCE, productRepository);
    }

}
