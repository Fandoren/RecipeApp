package com.surmin.recipe.service;

import com.surmin.recipe.exception.EntityNotFoundException;
import com.surmin.recipe.mapper.EntityToDtoMapper;
import com.surmin.recipe.model.DomainObject;
import com.surmin.recipe.model.DtoObject;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.util.StringUtils;

public abstract class CrudService <D extends DtoObject, E extends DomainObject, R extends MongoRepository<E, String>> {

    private final String ENTITY_ID_NOT_PROVIDED = "Entity Id wasn't provided";
    private final String OBJECT_DTO_NOT_PROVIDED =  "Object dto wasn't provided";
    private final String ENTITY_ID_SHOULD_NOT_BE_SPECIFIED = "Entity Id should not be manually specified";
    private final String OBJECT_DTO_ID_CANT_BE_NULL = "Object dto id can't be set to null";
    private final String MONGO_ENTITY_NOT_FOUND = "Entity was not found in mongoDB";

    private final EntityToDtoMapper<E, D> mapper;
    private final R mongoRepository;

    public CrudService(EntityToDtoMapper<E, D> mapper, R mongoRepository) {
        this.mapper = mapper;
        this.mongoRepository = mongoRepository;
    }

    protected R getMongoRepository() {
        return mongoRepository;
    }

    public D get(String entityId) {
        if(!StringUtils.hasText(entityId)) {
            throw new EntityNotFoundException(ENTITY_ID_NOT_PROVIDED);
        }
        E entity = mongoRepository.findById(entityId).orElse(null);
        return entity == null ? null : mapper.entityToDto(entity);
    }

    public Collection<D> getAll() {
        return mongoRepository.findAll().stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    public Page<D> getAll(Pageable pageable) {
        return mongoRepository.findAll(pageable).map(mapper::entityToDto);
    }

    public D save(D objectDto) {
        if(objectDto == null) {
            throw new EntityNotFoundException(OBJECT_DTO_NOT_PROVIDED);
        }
        if(objectDto.getEntityId() != null) {
            throw new IllegalArgumentException(ENTITY_ID_SHOULD_NOT_BE_SPECIFIED);
        }
        return mapper.entityToDto(mongoRepository.save(mapper.dtoToEntity(objectDto)));
    }

    public D update(D objectDto) {
        if(objectDto == null) {
            throw new EntityNotFoundException(OBJECT_DTO_NOT_PROVIDED);
        }
        if(objectDto.getEntityId() == null) {
            throw new IllegalArgumentException(OBJECT_DTO_ID_CANT_BE_NULL);
        }
        mongoRepository.findById(objectDto.getEntityId())
                .orElseThrow(() -> new EntityNotFoundException(MONGO_ENTITY_NOT_FOUND));
        E entity = mapper.dtoToEntity(objectDto);
        return mapper.entityToDto(mongoRepository.save(entity));
    }

    public void delete(D objectDto) {
        if(objectDto == null) {
            throw new IllegalArgumentException(OBJECT_DTO_NOT_PROVIDED);
        }
        E entity = mongoRepository.findById(objectDto.getEntityId())
                .orElseThrow(() -> new EntityNotFoundException(MONGO_ENTITY_NOT_FOUND));
        mongoRepository.delete(entity);
    }
}
