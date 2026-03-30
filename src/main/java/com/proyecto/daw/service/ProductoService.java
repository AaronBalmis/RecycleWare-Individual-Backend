package com.proyecto.daw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.daw.model.Producto;
import com.proyecto.daw.repository.DisponibilidadProductoRepository;
import com.proyecto.daw.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DisponibilidadProductoRepository disponibilidadProductoRepository;


    

    public List<Producto> findAll() {
        return productoRepository.findSqlAll();
    }

    public Producto findById(int id) {
        return productoRepository.findSqlById(id);
    }

    public List<Producto> findByCategoriaString(String categoria) {
        List<Producto> lista = productoRepository.findAll();
        
        for (Producto producto : lista) {
            if (producto.getCategoria().getNombre() == categoria) {
                lista.add(producto);
            }
        }
        return lista;
    }

    public List<Producto> findByEstadoString(String estado) {
        return null;
    }

    public List<Producto> findAllDisponibles(){
        List<Producto> lista = new ArrayList<Producto>();
        for (Producto producto : productoRepository.findAll()) {
            if (producto.getDisponibilidad().getId() == 1) {
                lista.add(producto);
            }
        }       
        return lista;
    }

    public Long count() {
        return productoRepository.count();
    }

    public boolean marcarProductoNoDisponibleById(int id){
        Producto p = productoRepository.findSqlById(id);
        if (p == null) {
            return false;
        }
        p.setDisponibilidad(disponibilidadProductoRepository.findSqlById(2));
        productoRepository.save(p);
        return true;
    }
}
