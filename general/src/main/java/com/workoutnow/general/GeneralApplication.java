package com.workoutnow.general;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeneralApplication {

	public static void main(String[] args) {
		preloadData();
		SpringApplication.run(GeneralApplication.class, args);
	}

	private static void preloadData() {
		String filePath = "";

	}

}
