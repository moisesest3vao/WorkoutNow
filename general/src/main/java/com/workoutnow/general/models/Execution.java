package com.workoutnow.general.models;

import com.workoutnow.general.dtos.ExecutionForm;
import com.workoutnow.general.enums.StatusExecution;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Execution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Training training;
    private StatusExecution status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    private String userId;

    public Execution(ExecutionForm form, Training training, String userId) {
        this.id = form.getId();
        this.training = training;
        this.status = StatusExecution.IN_PROGRESS;
        this.startDate = new Date();
        this.userId = userId;
    }
}
