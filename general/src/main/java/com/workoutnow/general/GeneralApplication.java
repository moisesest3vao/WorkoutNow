package com.workoutnow.general;

import com.workoutnow.general.enums.ExerciseDifficulty;
import com.workoutnow.general.enums.ExerciseType;
import com.workoutnow.general.models.Exercise;
import com.workoutnow.general.models.Training;
import com.workoutnow.general.repositories.ExerciseRepository;
import com.workoutnow.general.repositories.TrainingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class GeneralApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneralApplication.class, args);
	}


	@Bean
	CommandLineRunner init(TrainingRepository trainingRepository,
						   ExerciseRepository exerciseRepository) {
		return args -> {
			preloadData(trainingRepository, exerciseRepository);
		};
	}

	void preloadData(TrainingRepository trainingRepository, ExerciseRepository exerciseRepository) {
		List<Exercise> exercises = exerciseRepository.saveAll(getMockExercises());
		Training training = new Training();
		training.setName("NOME TESTE INTERMEDIATE");
		training.setExercises(exercises);
		training.setExerciseDifficulty(ExerciseDifficulty.INTERMEDIATE);
		trainingRepository.save(training);
	}


	List<Exercise> getMockExercises(){
		List<Exercise> exercises = new ArrayList<>();
		Exercise exercise = new Exercise();
		exercise.setExerciseType(ExerciseType.ARM);
		exercise.setRepetitions(10L);
		exercise.setName("Flexão de Braço");
		exercise.setExampleLink("youtube.com/watchasidajsdajdklsad");

		Exercise exercise2 = new Exercise();
		exercise2.setExerciseType(ExerciseType.ABS);
		exercise2.setTime(30L);
		exercise2.setName("Prancha");
		exercise2.setExampleLink("youtube.com/watchasidajsdajdklsad");

		exercises.add(exercise2);
		exercises.add(exercise);

		return exercises;
	}
}
