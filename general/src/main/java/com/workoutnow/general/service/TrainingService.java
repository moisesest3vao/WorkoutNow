package com.workoutnow.general.service;

import com.workoutnow.general.dtos.ExperimentalExecutionForm;
import com.workoutnow.general.dtos.TrainingDto;
import com.workoutnow.general.dtos.TrainingForm;
import com.workoutnow.general.models.Exercise;
import com.workoutnow.general.models.Training;
import com.workoutnow.general.repositories.ExerciseRepository;
import com.workoutnow.general.repositories.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;

    public TrainingDto create(TrainingForm form) {
        List<Long> exercisesId = form.getExercisesId();
        List<Exercise> exercises = exercisesId
                .stream().map(this::getTrainingById)
                .collect(Collectors.toList());

        if(exercises.contains(null)){
            return null;
        }

        Training training = new Training(form, exercises);
        Training entity = this.trainingRepository.save(training);
        return new TrainingDto(entity);
    }

    private Exercise getTrainingById(Long id) {
        return this.exerciseRepository.findById(id).orElse(null);
    }

    public Page<TrainingDto> getAll(Pageable pageable) {
        Page<Training> entityPage = this.trainingRepository.findAll(pageable);
        return entityPage.map(TrainingDto::new);
    }

    public Integer deleteById(Long id) {
        if(this.trainingRepository.existsById(id)){
            this.trainingRepository.deleteById(id);
            return 0;
        }
        return 1;
    }

    public TrainingDto createExperimentalTraining(ExperimentalExecutionForm form) {
        //send form data to analytics microservice

        List<Training> allExperimentalTrainings = this.trainingRepository.findAllExperimentalTrainings();
        Training training = allExperimentalTrainings.get(0);
        return training == null ? null : new TrainingDto(training);
    }
}
