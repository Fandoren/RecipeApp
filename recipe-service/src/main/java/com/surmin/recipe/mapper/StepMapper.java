package com.surmin.recipe.mapper;

import com.surmin.recipe.model.Step;
import com.surmin.recipe.model.StepDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StepMapper extends EntityToDtoMapper<Step, StepDto> {

    EntityToDtoMapper<Step, StepDto> INSTANCE = Mappers.getMapper(StepMapper.class);

    @Mapping(source = "id", target = "entityId")
    StepDto entityToDto(Step entity);

    @Mapping(source = "entityId", target = "id")
    Step dtoToEntity(StepDto dto);
}
