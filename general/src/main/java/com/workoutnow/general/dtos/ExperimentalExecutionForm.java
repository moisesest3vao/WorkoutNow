package com.workoutnow.general.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class ExperimentalExecutionForm {
    @NotNull
    @Range(max = 500, min = 10)
    private Long weight;
    @NotNull
    @Range(max = 300, min = 50)
    private Long height;
    @NotNull
    private boolean hasSportHistory;
}
