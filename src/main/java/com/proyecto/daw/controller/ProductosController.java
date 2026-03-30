package com.proyecto.daw.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.daw.model.Producto;
import com.proyecto.daw.service.CategoriasProductoService;
import com.proyecto.daw.service.EstadosProductoService;
import com.proyecto.daw.service.ProductoService;

@RequestMapping("/productos")

@RestController
public class ProductosController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriasProductoService categoriasProductoService;
    
    @Autowired
    private EstadosProductoService estadosProductoService;

    @GetMapping("")
    public ResponseEntity<List<Producto>> showProductos() {
        List<Producto> productos = productoService.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productos);

    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Producto>> showProductosDisponibles() {
        List<Producto> productos = productoService.findAllDisponibles();

        if (productos == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(productos);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> showProductoById(@PathVariable int id) {
        Producto producto = productoService.findById(id);

        if (producto == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(producto);
        }
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<String>> showAllCategorias() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoriasProductoService.findAllNombreCategorias());
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> showProductoByCategoriaString(@PathVariable String categoria) {
        List<Producto> productos = productoService.findByCategoriaString(categoria);

        if (productos == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(productos);
        }
    }

    @GetMapping("/estados")
    public ResponseEntity<List<String>> showAllEstados() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(estadosProductoService.findAllNombreEstados());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Producto>> showProductoByEstadoString(@PathVariable String estado) {
        List<Producto> productos = productoService.findByEstadoString(estado);

        if (productos == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(productos);
        }
    }

}
