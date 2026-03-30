package com.proyecto.daw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.daw.model.DisponibilidadProducto;

@Repository
public interface DisponibilidadProductoRepository extends JpaRepository<DisponibilidadProducto, Integer> {


    @Query(value = "SELECT nombre FROM disponibilidad_producto", nativeQuery = true)
    List<String> findAllNames(); 

    @Query(value = "SELECT * FROM disponibilidad_producto WHERE id = :id", nativeQuery = true)
    DisponibilidadProducto findSqlById(@Param("id") int id);
}