package com.surmin.recipe.controller;

import com.surmin.recipe.model.StepDto;
import com.surmin.recipe.service.StepService;
import java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/steps")
public class StepController {

    private final StepService stepService;

    StepController(StepService stepService) {
        this.stepService = stepService;
    }

    @GetMapping("/{entityId}")
    public StepDto get(@PathVariable("entityId") String entityId){
        return stepService.get(entityId);
    }

    @GetMapping
    public Collection<StepDto> getAll(){
        return stepService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StepDto save(@RequestBody StepDto stepDto) {
        return stepService.save(stepDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StepDto update(@RequestBody StepDto stepDto) {
        return stepService.update(stepDto);
    }

    @DeleteMapping("/{entityId}")
    public void delete(@PathVariable("entityId") String entityId) {
        StepDto stepDto = stepService.get(entityId);
        stepService.delete(stepDto);
    }

}
