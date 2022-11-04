package com.workoutnow.general.controllers;

import com.workoutnow.general.dtos.ExecutionDto;
import com.workoutnow.general.dtos.ExecutionForm;
import com.workoutnow.general.enums.StatusExecution;
import com.workoutnow.general.service.ExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("execution")
public class ExecutionController {
    @Autowired
    private ExecutionService executionService;

    @PostMapping
    public ResponseEntity<ExecutionDto> create(@RequestBody @Valid ExecutionForm form){
        ExecutionDto executionDto = this.executionService.create(form);
        return executionDto != null ? ResponseEntity.ok(executionDto) : ResponseEntity.badRequest().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<ExecutionDto> patch(@PathVariable Long id, @RequestParam StatusExecution status ){
        ExecutionDto executionDto = this.executionService.patch(id, status);
        return executionDto != null ? ResponseEntity.ok(executionDto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/me")
    public ResponseEntity<Page<ExecutionDto>> getAllUserExecution(@RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<ExecutionDto> result = this.executionService.findAllUserExecution(pageable);
        return ResponseEntity.ok(result);
    }

}
