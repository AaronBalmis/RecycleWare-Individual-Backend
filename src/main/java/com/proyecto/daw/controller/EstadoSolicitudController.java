package com.proyecto.daw.controller;

import com.proyecto.daw.model.RequestState;
import com.proyecto.daw.repository.RequestStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/config/estados-solicitud")
public class EstadoSolicitudController {

    @Autowired
    private RequestStateRepository repository;

    @GetMapping
    public List<RequestState> listar() {
        return repository.findAll();
    }

    @PostMapping
    public RequestState crear(@RequestBody RequestState estado) {
        return repository.save(estado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}