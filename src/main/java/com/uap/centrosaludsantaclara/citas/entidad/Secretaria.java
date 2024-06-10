package com.uap.centrosaludsantaclara.citas.entidad;

import com.uap.centrosaludsantaclara.autenticacion.entidad.Persona;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "secretaria")
@EntityListeners(AuditingEntityListener.class)
public class Secretaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_secretaria", nullable = false)
    private Long idSecretaria;

    @CreatedDate
    @Column(name = "_registro", updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "estado", nullable = false)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", nullable = false, foreignKey = @ForeignKey(name = "fk_secretaria_es_una_persona"))
    private Persona persona;
}
