package com.workoutnow.general.service;

import com.workoutnow.general.dtos.ExecutionDto;
import com.workoutnow.general.dtos.ExecutionForm;
import com.workoutnow.general.enums.StatusExecution;
import com.workoutnow.general.models.Execution;
import com.workoutnow.general.models.Training;
import com.workoutnow.general.repositories.ExecutionRepository;
import com.workoutnow.general.repositories.TrainingRepository;
//import com.workoutnow.general.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ExecutionService {
    @Autowired
    private ExecutionRepository executionRepository;
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private KeycloakService keycloakService;

    public ExecutionDto create(ExecutionForm form){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userId = jwt.getClaims().get("sub").toString();
        Training training = trainingRepository.findById(form.getTrainingId()).orElse(null);
        if(training == null){
            return null;
        }
        Execution execution = new Execution(form, training, userId);
        execution = this.executionRepository.save(execution);

        return new ExecutionDto(execution);
    }

    public ExecutionDto patch(Long id, StatusExecution status) {
        Execution execution = this.executionRepository.findById(id).orElse(null);
        if(execution == null){
            return null;
        }
        execution.setStatus(status);
        if(status == StatusExecution.FINISHED){
            execution.setEndDate(new Date());
        }
        execution = this.executionRepository.save(execution);

        return new ExecutionDto(execution);
    }

    public Page<ExecutionDto> findAllUserExecution(Pageable pageable) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id = jwt.getClaims().get("sub").toString();

        Page<Execution> queryResult = this.executionRepository.findAllByUserId(id, pageable);
        return queryResult.map(ExecutionDto::new);
    }
}
