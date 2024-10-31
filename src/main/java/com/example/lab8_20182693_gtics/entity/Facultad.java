package com.example.lab8_20182693_gtics.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "facultad")
@Getter
@Setter
public class Facultad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFacultad", nullable = false)
    private Integer idFacultad;

    @Column(name = "nombreFacultad", nullable = false, length = 15)
    private String nombreFacultad;


}