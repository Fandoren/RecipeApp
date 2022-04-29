package com.surmin.recipe.mapper;

import com.surmin.recipe.model.DomainObject;
import com.surmin.recipe.model.DtoObject;

public interface EntityToDtoMapper<D extends DtoObject, E extends DomainObject> {

    /**
     * Converts entity to dto object.
     *
     * @param entity - source instance
     * @return dto target instance
     */
    D entityToDto(E entity);

    /**
     * Converts dto to entity object.
     *
     * @param dto source instance
     * @return entity target instance
     */
    E dtoToEntity(D dto);
}
