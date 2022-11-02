package com.workoutnow.general.dtos;

import com.workoutnow.general.enums.StatusExecution;
import com.workoutnow.general.models.Execution;
import com.workoutnow.general.models.Training;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

@Data
public class ExecutionDto {
    private Long id;
    private Training training;
    private StatusExecution status;
    private Date start;
    private Date end;

    public ExecutionDto(Execution execution) {
        this.id = execution.getId();
        this.training = execution.getTraining();
        this.status = execution.getStatus();
        this.start = execution.getStart();
        this.end = execution.getEnd();
    }
}
