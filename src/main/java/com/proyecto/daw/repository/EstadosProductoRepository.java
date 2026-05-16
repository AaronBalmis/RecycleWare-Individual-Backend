package com.proyecto.daw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.daw.model.EstadosProducto;

@Repository
public interface EstadosProductoRepository extends JpaRepository<EstadosProducto, Integer> {

    @Query(value = "SELECT nombre FROM estados_producto", nativeQuery = true)
    List<String> findAllNames(); 

    @Query(value = "SELECT * FROM estados_producto WHERE id = :id ", nativeQuery = true)
    EstadosProducto findSqlById(@Param("id") int id);
}