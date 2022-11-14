package com.workoutnow.general;

import com.workoutnow.general.enums.ExerciseDifficulty;
import com.workoutnow.general.enums.ExerciseType;
import com.workoutnow.general.models.Exercise;
import com.workoutnow.general.models.Training;
import com.workoutnow.general.repositories.ExecutionRepository;
import com.workoutnow.general.repositories.ExerciseRepository;
import com.workoutnow.general.repositories.TrainingRepository;
import com.workoutnow.general.service.util.MockUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

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
						   ExerciseRepository exerciseRepository,
						   ExecutionRepository executionRepository,
						   KafkaTemplate<String, Object> kafkaTemplate) {
		Boolean wantsToPopulateEnvironmentWithMockData = false;
		String userId = "c159b52a-3569-4adf-8543-35bc0e40a478";

		if(wantsToPopulateEnvironmentWithMockData){
			return args -> {
				MockUtil.preloadData(trainingRepository, exerciseRepository, executionRepository,kafkaTemplate, userId);
			};
		}
	}


}
