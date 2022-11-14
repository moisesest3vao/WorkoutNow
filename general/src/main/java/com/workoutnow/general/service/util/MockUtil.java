package com.workoutnow.general.service.util;

import com.workoutnow.general.dtos.ExperimentalExecutionForm;
import com.workoutnow.general.dtos.TrainingFeedbackForm;
import com.workoutnow.general.enums.ExerciseDifficulty;
import com.workoutnow.general.enums.ExerciseType;
import com.workoutnow.general.enums.KafkaTopics;
import com.workoutnow.general.enums.StatusExecution;
import com.workoutnow.general.models.Execution;
import com.workoutnow.general.models.Exercise;
import com.workoutnow.general.models.Training;
import com.workoutnow.general.repositories.ExecutionRepository;
import com.workoutnow.general.repositories.ExerciseRepository;
import com.workoutnow.general.repositories.TrainingRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MockUtil {
    public static void preloadData(TrainingRepository trainingRepository,
                                   ExerciseRepository exerciseRepository,
                                   ExecutionRepository executionRepository,
                                   KafkaTemplate<String, Object> kafkaTemplate,
                                   String userId) {
        List<Exercise> exercises = exerciseRepository.saveAll(getMockExercises());
        List<Training> trainings = trainingRepository.saveAll(getMockTrainings(exercises));
        executionRepository.saveAll(getMockExecutions(trainings, userId));
        List<ExperimentalExecutionForm> listForm = getMockExperimentalForm();
        listForm.forEach(form -> {
            kafkaTemplate.send(KafkaTopics.USER_HEALTH_DATA_TOPIC.topic, userId, form);
        });
        List<TrainingFeedbackForm> listFeedbackForm = getMockFeedbackForm();
        listFeedbackForm.forEach(form -> {
            kafkaTemplate.send(KafkaTopics.TRAINING_FEEDBACK_TOPIC.topic, userId, form);
        });
    }

    private static List<TrainingFeedbackForm> getMockFeedbackForm() {
        List<TrainingFeedbackForm> feedbacks = new ArrayList<>();
        feedbacks.add(new TrainingFeedbackForm(
                1L,
                true,
                7
        ));

        feedbacks.add(new TrainingFeedbackForm(
                2L,
                false,
                4
        ));

        feedbacks.add(new TrainingFeedbackForm(
                3L,
                true,
                10
        ));

        feedbacks.add(new TrainingFeedbackForm(
                1L,
                false,
                6
        ));

        return feedbacks;
    }

    private static List<ExperimentalExecutionForm> getMockExperimentalForm() {
        List<ExperimentalExecutionForm> experimentals = new ArrayList<>();
        experimentals.add(new ExperimentalExecutionForm(
                50L,
                150L,
                false
        ));

        experimentals.add(new ExperimentalExecutionForm(
                55L,
                151L,
                true
        ));

        experimentals.add(new ExperimentalExecutionForm(
                52L,
                152L,
                true
        ));

        return experimentals;
    }

    private static List<Execution> getMockExecutions(List<Training> trainings, String userId) {
        List<Execution> executions = new ArrayList<>();

        Execution execution = new Execution(trainings.get(0), userId);
        Execution execution1 = new Execution(trainings.get(0), userId);
        Execution execution2 = new Execution(trainings.get(0), userId);
        Execution execution3 = new Execution(trainings.get(0), userId);
        Execution execution4 = new Execution(trainings.get(0), userId);

        Execution execution5 = new Execution(trainings.get(1), userId);
        execution5.setStatus(StatusExecution.ERROR);

        Execution execution6 = new Execution(trainings.get(1), userId);
        execution6.setEndDate(new Date());
        execution6.setStatus(StatusExecution.FINISHED);

        Execution execution7 = new Execution(trainings.get(1), userId);
        execution7.setEndDate(new Date());
        execution7.setStatus(StatusExecution.FINISHED);

        Execution execution8 = new Execution(trainings.get(2), userId);
        execution8.setEndDate(new Date());
        execution8.setStatus(StatusExecution.FINISHED);

        Execution execution9 = new Execution(trainings.get(2), userId);
        execution9.setEndDate(new Date());
        execution9.setStatus(StatusExecution.FINISHED);

        Execution execution10 = new Execution(trainings.get(0), userId);
        execution10.setEndDate(new Date());
        execution10.setStatus(StatusExecution.FINISHED);

        Execution execution11 = new Execution(trainings.get(0), userId);
        execution11.setEndDate(new Date());
        execution11.setStatus(StatusExecution.FINISHED);

        Execution execution12 = new Execution(trainings.get(3), userId);
        execution12.setEndDate(new Date());
        execution12.setStatus(StatusExecution.FINISHED);

        executions.add(execution);
        executions.add(execution1);
        executions.add(execution2);
        executions.add(execution3);
        executions.add(execution4);
        executions.add(execution5);
        executions.add(execution6);
        executions.add(execution7);
        executions.add(execution8);
        executions.add(execution9);
        executions.add(execution10);
        executions.add(execution11);
        executions.add(execution12);

        return executions;
    }

    private static List<Training> getMockTrainings(List<Exercise> exercises) {
        List<Training> trainings = new ArrayList<>();

        Training training = new Training();
        training.setName("Iniciante Corpo Completo");
        training.setExercises(exercises);
        training.setExerciseDifficulty(ExerciseDifficulty.BEGINNER);

        Training training1 = new Training();
        training1.setName("Intermediário Corpo Completo");
        training1.setExercises(exercises);
        training1.setExerciseDifficulty(ExerciseDifficulty.INTERMEDIATE);

        Training training2 = new Training();
        training2.setName("Avançado Corpo Completo");
        training2.setExercises(exercises);
        training2.setExerciseDifficulty(ExerciseDifficulty.ADVANCED);

        Training experimentalTraining = new Training();
        experimentalTraining.setName("Experimental Corpo Completo");
        experimentalTraining.setExercises(exercises);
        experimentalTraining.setExerciseDifficulty(ExerciseDifficulty.EXPERIMENTAL);

        trainings.add(training);
        trainings.add(training1);
        trainings.add(training2);
        trainings.add(experimentalTraining);

        return trainings;
    }


    private static List<Exercise> getMockExercises(){
        List<Exercise> exercises = new ArrayList<>();

        Exercise exercise = new Exercise();
        exercise.setExerciseType(ExerciseType.ARM);
        exercise.setRepetitions(10L);
        exercise.setName("Flexão de braço");
        exercise.setExampleLink("https://youtu.be/dQw4w9WgXcQ");

        Exercise exercise2 = new Exercise();
        exercise2.setExerciseType(ExerciseType.ABS);
        exercise2.setTime(10L);
        exercise2.setName("Prancha");
        exercise2.setExampleLink("https://youtu.be/dQw4w9WgXcQ");

        Exercise exercise3 = new Exercise();
        exercise3.setExerciseType(ExerciseType.LEGS);
        exercise3.setRepetitions(10L);
        exercise3.setName("Agachamento");
        exercise3.setExampleLink("https://youtu.be/dQw4w9WgXcQ");

        Exercise exercise4 = new Exercise();
        exercise4.setExerciseType(ExerciseType.BACK);
        exercise4.setRepetitions(10L);
        exercise4.setName("Barra");
        exercise4.setExampleLink("https://youtu.be/dQw4w9WgXcQ");

        Exercise exercise5 = new Exercise();
        exercise5.setExerciseType(ExerciseType.SHOLDER);
        exercise5.setRepetitions(10L);
        exercise5.setName("Elevação Lateral");
        exercise5.setExampleLink("https://youtu.be/dQw4w9WgXcQ");

        Exercise exercise6 = new Exercise();
        exercise6.setExerciseType(ExerciseType.CHEST);
        exercise6.setRepetitions(10L);
        exercise6.setName("Flexão de peito");
        exercise6.setExampleLink("https://youtu.be/dQw4w9WgXcQ");

        Exercise exercise7 = new Exercise();
        exercise7.setExerciseType(ExerciseType.CARDIO);
        exercise7.setTime(30L);
        exercise7.setName("Polichinelo");
        exercise7.setExampleLink("https://youtu.be/dQw4w9WgXcQ");

        exercises.add(exercise);
        exercises.add(exercise2);
        exercises.add(exercise3);
        exercises.add(exercise4);
        exercises.add(exercise5);
        exercises.add(exercise6);
        exercises.add(exercise7);

        return exercises;
    }
}
