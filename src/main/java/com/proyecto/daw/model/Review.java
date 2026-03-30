package com.proyecto.daw.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "resenas")
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String texto;

    @Column(nullable = false, length = 80)
    private String autor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 45)
    private Rol rol;

    private Integer estrellas;

    private Boolean activa;
}