package com.workoutnow.general.service;

import com.workoutnow.general.dtos.ExperimentalExecutionForm;
import com.workoutnow.general.dtos.TrainingDto;
import com.workoutnow.general.dtos.TrainingFeedbackForm;
import com.workoutnow.general.dtos.TrainingForm;
import com.workoutnow.general.enums.KafkaTopics;
import com.workoutnow.general.models.Exercise;
import com.workoutnow.general.models.Training;
import com.workoutnow.general.repositories.ExerciseRepository;
import com.workoutnow.general.repositories.TrainingRepository;
import com.workoutnow.general.service.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TrainingService {
    private final TrainingRepository trainingRepository;
    private final ExerciseRepository exerciseRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository, ExerciseRepository exerciseRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.trainingRepository = trainingRepository;
        this.exerciseRepository = exerciseRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

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
        String userId=UserUtil.getCurrentUserId();
        //send form data to analytics microservice
        this.kafkaTemplate.send(KafkaTopics.USER_HEALTH_DATA_TOPIC.topic, userId, form);

        List<Training> allExperimentalTrainings = this.trainingRepository.findAllExperimentalTrainings();
        int randomIndex = new Random().nextInt(allExperimentalTrainings.size());
        Training training = allExperimentalTrainings.get(randomIndex);

        return training == null ? null : new TrainingDto(training);
    }

    public Integer doFeedbackTreatment(TrainingFeedbackForm form) {
        if(this.trainingRepository.existsById(form.getTrainingId())){
            String currentUserId = UserUtil.getCurrentUserId();
            ListenableFuture<SendResult<String, Object>> listenable =
                    this.kafkaTemplate.send(KafkaTopics.TRAINING_FEEDBACK_TOPIC.topic, currentUserId, form);
            return 0;
        }
        return 1;
    }
}
