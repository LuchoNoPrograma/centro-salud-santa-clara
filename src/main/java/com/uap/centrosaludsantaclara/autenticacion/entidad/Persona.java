package com.uap.centrosaludsantaclara.autenticacion.entidad;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "persona", uniqueConstraints = @UniqueConstraint(name = "ci_debe_ser_unico", columnNames = "ci"))
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "nombres", nullable = false, length = 55)
    private String nombres;

    @Column(name = "paterno", length = 55)
    private String paterno;

    @Column(name = "materno", length = 55)
    private String materno;

    @Column(name = "ci", length = 30, nullable = false)
    private String ci;

    @Column(name = "sexo", length = 1, nullable = false)
    private Character sexo;//F = FEMENINO | M = MASCULINO

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @OneToOne(mappedBy = "persona")
    private Administrador administrador;

    @Transient
    private String codigoAcceso;
}
