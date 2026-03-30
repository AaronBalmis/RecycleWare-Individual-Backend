package com.proyecto.daw.controller;

import com.proyecto.daw.exception.ResourceNotFoundException;
import com.proyecto.daw.model.DonationState;
import com.proyecto.daw.repository.DonationStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donation-states")
public class DonationStateController {

    @Autowired
    private DonationStateRepository donationStateRepository;

    @GetMapping
    public List<DonationState> getAll() {
        return donationStateRepository.findAll();
    }

    @GetMapping("/{id}")
    public DonationState getById(@PathVariable Integer id) {
        return donationStateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado de donación no encontrado con ID: " + id));
    }
}