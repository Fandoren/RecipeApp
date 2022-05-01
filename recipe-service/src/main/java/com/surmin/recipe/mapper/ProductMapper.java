package com.surmin.recipe.mapper;

import com.surmin.recipe.model.Product;
import com.surmin.recipe.model.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper extends EntityToDtoMapper<Product, ProductDto> {

    EntityToDtoMapper<Product, ProductDto> INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "id", target = "entityId")
    ProductDto entityToDto(Product entity);

    @Mapping(source = "entityId", target = "id")
    Product dtoToEntity(ProductDto dto);
}

