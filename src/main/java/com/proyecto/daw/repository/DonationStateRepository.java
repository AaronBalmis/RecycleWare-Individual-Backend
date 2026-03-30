package com.proyecto.daw.repository;

import com.proyecto.daw.model.DonationState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationStateRepository extends JpaRepository<DonationState, Integer> {
}