package com.surmin.recipe.service;

import com.surmin.recipe.mapper.StepMapper;
import com.surmin.recipe.model.Step;
import com.surmin.recipe.model.StepDto;
import com.surmin.recipe.repository.StepRepository;
import org.springframework.stereotype.Service;

@Service
public class StepService extends CrudService<StepDto, Step, StepRepository> {

    public StepService(StepRepository stepRepository) {
        super(StepMapper.INSTANCE, stepRepository);
    }

}
