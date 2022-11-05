package com.workoutnow.general.models;

import com.workoutnow.general.enums.StatusExecution;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Enumerated(EnumType.STRING)
    private StatusExecution status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    private String userId;

    public Execution(Training training, String userId) {
        this.training = training;
        this.status = StatusExecution.IN_PROGRESS;
        this.startDate = new Date();
        this.userId = userId;
    }
}
