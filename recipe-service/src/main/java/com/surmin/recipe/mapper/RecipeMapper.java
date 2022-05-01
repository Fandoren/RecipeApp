package com.surmin.recipe.mapper;

import com.surmin.recipe.model.Recipe;
import com.surmin.recipe.model.RecipeDto;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface RecipeMapper extends EntityToDtoMapper<Recipe, RecipeDto> {

    EntityToDtoMapper<Recipe, RecipeDto> INSTANCE = Mappers.getMapper(RecipeMapper.class);

    @Mapping(source = "id", target = "entityId")
    RecipeDto entityToDto(Recipe entity);

    @Mapping(source = "entityId", target = "id")
    Recipe dtoToEntity(RecipeDto dto);
}
