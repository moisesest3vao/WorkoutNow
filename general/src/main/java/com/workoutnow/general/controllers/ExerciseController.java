package com.workoutnow.general.controllers;

import com.workoutnow.general.dtos.ExerciseDto;
import com.workoutnow.general.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("exercise")
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<ExerciseDto> create(@RequestBody @Valid ExerciseDto exercise){
        ExerciseDto exerciseDto = this.exerciseService.create(exercise);
        return exerciseDto != null ? ResponseEntity.ok(exerciseDto) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    @RolesAllowed({"admin"})
    public ResponseEntity<Page<ExerciseDto>> getAll(@RequestParam Integer size, @RequestParam Integer page){
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        Page<ExerciseDto> exercisesDto = this.exerciseService.getAll(pageable);
        return exercisesDto != null ? ResponseEntity.ok(exercisesDto) : ResponseEntity.badRequest().build();
    }
}
