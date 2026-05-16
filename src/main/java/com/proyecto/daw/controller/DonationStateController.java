package com.proyecto.daw.controller;

import com.proyecto.daw.model.DonationState;
import com.proyecto.daw.repository.DonationStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config/estados-donacion")
public class DonationStateController {

    @Autowired
    private DonationStateRepository repository;

    @GetMapping
    public List<DonationState> listar() {
        return repository.findAll();
    }

    @PostMapping
    public DonationState crear(@RequestBody DonationState estado) {
        return repository.save(estado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}