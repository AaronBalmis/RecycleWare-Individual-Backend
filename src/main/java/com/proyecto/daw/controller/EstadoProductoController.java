package com.proyecto.daw.controller;

import com.proyecto.daw.model.EstadosProducto;
import com.proyecto.daw.repository.EstadosProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/config/estados-producto")
public class EstadoProductoController {

    @Autowired
    private EstadosProductoRepository repository;

    @GetMapping
    public List<EstadosProducto> listar() {
        return repository.findAll();
    }

    @PostMapping
    public EstadosProducto crear(@RequestBody EstadosProducto estado) {
        return repository.save(estado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}