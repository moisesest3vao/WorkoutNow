package com.workoutnow.general.repositories;

import com.workoutnow.general.models.Execution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionRepository extends JpaRepository<Execution, Long> {
}
