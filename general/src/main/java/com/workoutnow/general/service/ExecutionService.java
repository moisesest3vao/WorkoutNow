package com.workoutnow.general.service;

import com.workoutnow.general.dtos.ExecutionDto;
import com.workoutnow.general.dtos.ExecutionForm;
import com.workoutnow.general.enums.StatusExecution;
import com.workoutnow.general.models.Execution;
import com.workoutnow.general.models.Training;
import com.workoutnow.general.repositories.ExecutionRepository;
import com.workoutnow.general.repositories.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExecutionService {
    @Autowired
    private ExecutionRepository executionRepository;
    @Autowired
    private TrainingRepository trainingRepository;

    public ExecutionDto create(ExecutionForm form){
        Training training = trainingRepository.findById(form.getTrainingId()).orElse(null);
        if(training == null){
            return null;
        }
        Execution execution = new Execution(form, training);
        return new ExecutionDto(execution);
    }

    public ExecutionDto patch(Long id, StatusExecution status) {
        Execution execution = this.executionRepository.findById(id).orElse(null);
        if(execution == null){
            return null;
        }
        execution.setStatus(status);
        execution = this.executionRepository.save(execution);

        return new ExecutionDto(execution);
    }
}
