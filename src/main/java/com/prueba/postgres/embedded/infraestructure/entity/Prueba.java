package com.prueba.postgres.embedded.infraestructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Table(name = "pruebas")
public class Prueba extends Auditing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String code;

    // Permite nulos
    private String description;

    @Column(name = "is_default")
    @EqualsAndHashCode.Include
    private Boolean isDefault;

    @Column(name = "active", columnDefinition = "varchar(1) default 'S'")
    @EqualsAndHashCode.Include
    private Boolean active;
}