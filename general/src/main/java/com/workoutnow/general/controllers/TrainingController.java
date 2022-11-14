package com.workoutnow.general.controllers;

import com.workoutnow.general.dtos.ExperimentalExecutionForm;
import com.workoutnow.general.dtos.TrainingDto;
import com.workoutnow.general.dtos.TrainingFeedbackForm;
import com.workoutnow.general.dtos.TrainingForm;
import com.workoutnow.general.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("training")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @PostMapping("experimental")
    public ResponseEntity<TrainingDto> createExperimentalTraining(@RequestBody @Valid ExperimentalExecutionForm form ) {
        TrainingDto experimentalTraining = this.trainingService.createExperimentalTraining(form);

        return experimentalTraining != null ? ResponseEntity.ok(experimentalTraining) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @RolesAllowed({"training_management"})
    public ResponseEntity<TrainingDto> create(@RequestBody @Valid TrainingForm form){
        TrainingDto trainingDto = this.trainingService.create(form);
        return trainingDto != null ? ResponseEntity.ok(trainingDto) : ResponseEntity.badRequest().build();
    }
    @GetMapping
    public ResponseEntity<Page<TrainingDto>> getAll(@RequestParam Integer size, @RequestParam Integer page){
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        Page<TrainingDto> trainingDtos = this.trainingService.getAll(pageable);
        return trainingDtos != null ? ResponseEntity.ok(trainingDtos) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("{id}")
    @RolesAllowed({"training_management"})
    public ResponseEntity<?> delete(@PathVariable Long id){
        Integer response = this.trainingService.deleteById(id);
        return response != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("feedback")
    public ResponseEntity<TrainingDto> getTrainingFeedback(@RequestBody @Valid TrainingFeedbackForm form){
        Integer response = this.trainingService.doFeedbackTreatment(form);
        return response == 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
