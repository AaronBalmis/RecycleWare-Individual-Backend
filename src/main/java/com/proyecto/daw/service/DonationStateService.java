package com.proyecto.daw.service;

import com.proyecto.daw.exception.ResourceNotFoundException;
import com.proyecto.daw.model.DonationState;
import com.proyecto.daw.repository.DonationStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DonationStateService {
    @Autowired
    private DonationStateRepository donationStateRepository;

    public List<DonationState> findAll() {
        return donationStateRepository.findAll();
    }

    public DonationState findById(Integer id) {
        return donationStateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado con ID: " + id));
    }
}