package com.workoutnow.general.service;

import com.workoutnow.general.dtos.*;
import com.workoutnow.general.enums.ExerciseDifficulty;
import com.workoutnow.general.models.Training;
import com.workoutnow.general.repositories.ExerciseRepository;
import com.workoutnow.general.repositories.TrainingRepository;
import com.workoutnow.general.service.util.UserUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TrainingServiceTest {
    private TrainingService trainingService ;
    @Mock
    private ExerciseRepository exerciseRepository;
    @Mock
    private TrainingRepository trainingRepository;
    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @BeforeEach
    private void initialize(){
        MockitoAnnotations.initMocks(this);
        this.trainingService = new TrainingService(trainingRepository, exerciseRepository, kafkaTemplate);
    }

    @Test
    void shouldReturnExperimentalTrainingAndSendDataToKafka(){
        ExperimentalExecutionForm form = new ExperimentalExecutionForm();

        Mockito.when(this.trainingRepository.findAllExperimentalTrainings()).thenReturn(getListTrainings());
        Mockito.when(this.trainingRepository.findById(1L)).thenReturn(Optional.of(new Training()));

        try (MockedStatic<UserUtil> utilities = Mockito.mockStatic(UserUtil.class)) {
            utilities.when(UserUtil::getCurrentUserId).thenReturn("idteste");
            TrainingDto trainingDto = this.trainingService.createExperimentalTraining(form);
            Mockito.verify(this.kafkaTemplate).send(Mockito.any(), Mockito.any(), Mockito.any());
        }
    }

    @Test
    void shouldDoFeedbackTreatment(){
        TrainingFeedbackForm form = new TrainingFeedbackForm();
        Mockito.when(this.trainingRepository.existsById(Mockito.any())).thenReturn(true);

        try (MockedStatic<UserUtil> utilities = Mockito.mockStatic(UserUtil.class)) {
            utilities.when(UserUtil::getCurrentUserId).thenReturn("idteste");
            Integer integer = this.trainingService.doFeedbackTreatment(form);
            assertEquals(0, integer);
            Mockito.verify(this.kafkaTemplate).send(Mockito.any(), Mockito.any(), Mockito.any());
        }
    }

    private List<Training> getListTrainings() {
        List list = new ArrayList<>();
        Training training = new Training(
                1L,
                "NOME",
                ExerciseDifficulty.EXPERIMENTAL,
                new ArrayList<>()
        );
        list.add(training);
        return list;
    }
}