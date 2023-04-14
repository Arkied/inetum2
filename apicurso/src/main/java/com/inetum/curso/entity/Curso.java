package com.inetum.curso.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "curso")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "codigo_c")
    private String codigo;
    @Column(name = "descripcion", length = 50)
    private String descripcion;
    @Column(name = "cod_prof", length = 3)
    private String cod_profesor;
    @Column(name = "codigo_alum", length = 5)
    private String cod_alumno;
}
