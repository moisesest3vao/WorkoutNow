package com.workoutnow.general.models;

import com.workoutnow.general.dtos.ExerciseDto;
import com.workoutnow.general.enums.ExerciseType;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String exampleLink;
    @Enumerated(EnumType.STRING)
    private ExerciseType exerciseType;
    private Long repetitions;
    private Long time;

    public Exercise(ExerciseDto dto){
        this.id = dto.getId();
        this.name = dto.getName();
        this.exampleLink = dto.getExampleLink();
        this.exerciseType = dto.getExerciseType();
        this.repetitions = dto.getRepetitions();
        this.time = dto.getTime();
    }
}
