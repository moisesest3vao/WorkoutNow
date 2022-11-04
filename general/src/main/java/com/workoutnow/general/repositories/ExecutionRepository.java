package com.workoutnow.general.repositories;

import com.workoutnow.general.models.Execution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExecutionRepository extends JpaRepository<Execution, Long> {
    Page<Execution> findAllByUserId(String id, Pageable pageable);
}
