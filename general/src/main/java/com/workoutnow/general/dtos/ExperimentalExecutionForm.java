package com.workoutnow.general.dtos;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ExperimentalExecutionForm {
    @NotNull
    @Size(max = 500, min = 1)
    private Long weight;
    @NotNull
    @Size(max = 300, min = 50)
    private Long height;
    @NotNull
    private boolean hasSportHistory;
}
