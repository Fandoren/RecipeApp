package com.surmin.recipe.service;

import com.surmin.recipe.mapper.EntityToDtoMapper;
import com.surmin.recipe.model.DomainObject;
import com.surmin.recipe.model.DtoObject;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public abstract class CrudService <D extends DtoObject, E extends DomainObject, R extends MongoRepository<E, String>> {

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
        return mapper.entityToDto(mongoRepository.save(mapper.dtoToEntity(objectDto)));
    }

    public D update(D objectDto) {
        E retrievedEntity = mongoRepository.findById(objectDto.getEntityId())
                .orElseThrow(() -> new UnsupportedOperationException());
        E entity = mapper.dtoToEntity(objectDto);
        return mapper.entityToDto(mongoRepository.save(entity));
    }

    public void delete(D objectDto) {
        E entity = mongoRepository.findById(objectDto.getEntityId())
                .orElseThrow(() -> new UnsupportedOperationException());
        mongoRepository.delete(entity);
    }
}
