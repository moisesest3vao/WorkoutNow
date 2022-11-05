package com.workoutnow.general.repositories;

import com.workoutnow.general.models.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    @Query(value = "SELECT * FROM training WHERE training.exercise_difficulty = 'EXPERIMENTAL'", nativeQuery = true)
    List<Training> findAllExperimentalTrainings();
}
