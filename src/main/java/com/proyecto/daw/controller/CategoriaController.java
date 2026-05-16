package com.proyecto.daw.controller; 

import com.proyecto.daw.model.Categoria;
import com.proyecto.daw.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config/categorias")
public class CategoriaController {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listar() {
    System.out.println("Entrando al endpoint de categorias..."); 
    return categoriaRepository.findAll();
}

    @PostMapping
    public Categoria crear(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        categoriaRepository.deleteById(id);
    }
}