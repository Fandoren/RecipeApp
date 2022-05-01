package com.surmin.recipe.mapper;

import com.surmin.recipe.model.Tag;
import com.surmin.recipe.model.TagDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper extends EntityToDtoMapper<Tag, TagDto> {

    EntityToDtoMapper<Tag, TagDto> INSTANCE = Mappers.getMapper(TagMapper.class);

    @Mapping(source = "id", target = "entityId")
    TagDto entityToDto(Tag entity);

    @Mapping(source = "entityId", target = "id")
    Tag dtoToEntity(TagDto dto);
}
