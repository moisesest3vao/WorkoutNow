package com.workoutnow.general.service;

import com.workoutnow.general.dtos.ExerciseDto;
import com.workoutnow.general.models.Exercise;
import com.workoutnow.general.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {
    @Autowired
    ExerciseRepository exerciseRepository;

    public ExerciseDto create(ExerciseDto dto){
        Exercise exercise = new Exercise(dto);
        exercise = exerciseRepository.save(exercise);

        return new ExerciseDto(exercise);
    }


    public Page<ExerciseDto> getAll(Pageable pageable) {
        Page<Exercise> pageEntity = exerciseRepository.findAll(pageable);
        return pageEntity.map(ExerciseDto::new);
    }


    public Integer deleteById(Long id) {
        if(this.exerciseRepository.existsById(id)){
            this.exerciseRepository.deleteById(id);
            return 0;
        }
        return 1;
    }
}
