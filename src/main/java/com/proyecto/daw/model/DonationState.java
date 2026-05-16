package com.proyecto.daw.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "estados_donacion")
@Data
public class DonationState {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String nombre;
}