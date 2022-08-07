package com.surmin.recipe.service;

import com.surmin.recipe.exception.EntityNotFoundException;
import com.surmin.recipe.mapper.EntityToDtoMapper;
import com.surmin.recipe.mapper.ProductMapper;
import com.surmin.recipe.model.Product;
import com.surmin.recipe.model.ProductDto;
import com.surmin.recipe.repository.ProductRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends CrudService<ProductDto, Product, ProductRepository>{

    private static final String PRODUCT_WAS_NOT_FOUND = "Product entity wasn't found";
    private static final String IMAGE_IS_EMPTY = "Image can't be empty";
    private static final String IMAGE_IS_OVERSIZED = "Image oversized, it should not exceed 1MB";

    public static final int IMAGE_BYTES_LIMIT = 1 << 20;

    public ProductService(ProductRepository productRepository) {
        super(ProductMapper.INSTANCE, productRepository);
    }

    public Page<ProductDto> getPage(Integer pageId) {
        Pageable pageable = PageRequest.of(pageId, 8);
        return getAll(pageable);
    }

    public Page<ProductDto> getPageFilterByTagIds(Integer pageId, Collection<String> tagIds) {
        Pageable pageable = PageRequest.of(pageId, 8);
        return getMongoRepository().findByTagIdsIn(tagIds, pageable);
    }

    public Product getProduct(String entityId) {
        Optional<Product> productOptional = getMongoRepository().findById(entityId);
        if (productOptional.isEmpty()) {
            throw new EntityNotFoundException(PRODUCT_WAS_NOT_FOUND);
        }
        return productOptional.get();
    }

    public Collection<ProductDto> getProductsByTagId(String entityId) {
        Collection<Product> products = getMongoRepository().findByTagIdsIn(entityId);
        return products.stream().map(getMapper()::entityToDto).collect(Collectors.toList());
    }

    public Collection<ProductDto> getProductsByIds(Collection<String> entityIds) {
        List<ProductDto> products = new ArrayList<>();
        EntityToDtoMapper<Product, ProductDto> mapper = getMapper();
        getMongoRepository().findAllById(entityIds).forEach(product -> {
            products.add(mapper.entityToDto(product));
        });
        return products;
    }

    public void saveImage(String entityId, byte[] imageAsByteArray) throws IOException {
        if (imageAsByteArray == null || imageAsByteArray.length <= 0) {
            throw new IllegalArgumentException(IMAGE_IS_EMPTY);
        }
        if (imageAsByteArray.length > IMAGE_BYTES_LIMIT) {
            throw new IllegalArgumentException(IMAGE_IS_OVERSIZED);
        }
        Optional<Product> productOptional = getMongoRepository().findById(entityId);
        if (productOptional.isEmpty()) {
            throw new EntityNotFoundException(PRODUCT_WAS_NOT_FOUND);
        }
        Product product = productOptional.get();
        product.setImageAsByteArray(imageAsByteArray);
        getMongoRepository().save(product);
    }

}
