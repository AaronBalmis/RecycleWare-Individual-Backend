package com.proyecto.daw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.proyecto.daw.model.RequestState;

@Repository
public interface RequestStateRepository extends JpaRepository<RequestState, Integer> {
    Optional<RequestState> findByNombre(String nombre);
}