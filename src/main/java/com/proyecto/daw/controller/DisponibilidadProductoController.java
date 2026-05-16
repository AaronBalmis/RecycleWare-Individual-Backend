package com.proyecto.daw.controller;

import com.proyecto.daw.model.DisponibilidadProducto;
import com.proyecto.daw.repository.DisponibilidadProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/config/disponibilidad")
public class DisponibilidadProductoController {

    @Autowired
    private DisponibilidadProductoRepository repository;

    @GetMapping
    public List<DisponibilidadProducto> listar() {
        return repository.findAll();
    }

    @PostMapping
    public DisponibilidadProducto crear(@RequestBody DisponibilidadProducto estado) {
        return repository.save(estado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}