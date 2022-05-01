package com.surmin.recipe.repository;

import com.surmin.recipe.model.Step;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepRepository extends MongoRepository<Step, String> {

}
