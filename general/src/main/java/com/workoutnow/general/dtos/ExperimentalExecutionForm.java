package com.workoutnow.general.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
