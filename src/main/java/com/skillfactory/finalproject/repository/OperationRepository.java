package com.skillfactory.finalproject.repository;

import com.skillfactory.finalproject.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    @Query(value = "SELECT * FROM public.\"Operations\" WHERE client_id = ?1", nativeQuery = true)
    List<Operation> findAllByClientId(Long id);

    @Query(value = "SELECT * FROM public.\"Operations\" WHERE client_id = ?1 and time_of_operation between ?2 and ?3", nativeQuery = true)
    List<Operation> findAllFromTo(Long id, LocalDateTime from, LocalDateTime to);
}
