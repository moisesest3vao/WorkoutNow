package com.workoutnow.general.dtos;

import com.workoutnow.general.enums.StatusExecution;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
@Data
public class ExecutionForm {
    @Null
    private Long id;
    @NotNull
    private Long trainingId;
    @NotNull
    private StatusExecution status;
}
