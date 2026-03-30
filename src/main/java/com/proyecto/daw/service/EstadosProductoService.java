package com.proyecto.daw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.daw.model.EstadosProducto;
import com.proyecto.daw.repository.EstadosProductoRepository;

@Service
public class EstadosProductoService {

    @Autowired
    private EstadosProductoRepository estadosProductoRepository;


    

    public List<EstadosProducto> findAll() {
        return estadosProductoRepository.findAll();
    }

    public EstadosProducto findById(int id) {
        return estadosProductoRepository.findSqlById(id);
    }

    public List<String> findAllNombreEstados(){
        return estadosProductoRepository.findAllNames();
    }
}