package com.uap.centrosaludsantaclara.citas.entidad;

import com.uap.centrosaludsantaclara.autenticacion.entidad.Persona;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "paciente")
@EntityListeners(AuditingEntityListener.class)
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente", nullable = false)
    private Long idPaciente;

    @CreatedDate
    @Column(name = "_paciente", updatable = false)
    private LocalDateTime fechaHoraInscripcion;

    @Column(name = "estado", nullable = false)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_persona", nullable = false, foreignKey = @ForeignKey(name = "fk_paciente_es_una_persona"))
    private Persona persona;

    @PrePersist
    public void datosPorDefecto() {
        this.estado = "ACTIVO";
    }
}