package com.example.lab8_20182693_gtics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "estudiante")
@Getter
@Setter
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 40)
    private String nombre;



    @ManyToOne
    @JoinColumn(name = "idFacultad")
    private Facultad facultad;



    @Column(name = "gpa", precision = 10, scale = 4)
    private BigDecimal gpa;

    @Column(name = "creditosCompletados", precision = 10, scale = 4)
    private BigDecimal creditosCompletados;



}