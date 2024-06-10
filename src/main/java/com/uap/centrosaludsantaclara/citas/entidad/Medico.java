package com.uap.centrosaludsantaclara.citas.entidad;

import com.uap.centrosaludsantaclara.autenticacion.entidad.Persona;
import com.uap.centrosaludsantaclara.citas.enums.Especialidad;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "medico")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico", nullable = false)
    private Long idMedico;

    @Column(name = "fecha_inicio_trabajo", nullable = false)
    private LocalDate fechaInicioTrabajo;

    @Enumerated(EnumType.STRING)
    @Column(name = "especialidad", nullable = false)
    private Especialidad especialidad;

    @Column(name = "estado", nullable = false)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", nullable = false, foreignKey = @ForeignKey(name = "fk_medico_es_una_persona"))
    private Persona persona;
}