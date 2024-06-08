package com.uap.centrosaludsantaclara.autenticacion.entidad;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "administrador")
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_administrador", nullable = false)
    private Long idAdministrador;

    @Column(name = "codigo_acceso", nullable = false)
    private String codigoAcceso;

    @Column(name = "estado_admin")
    private String estadoAdmin;//ACTIVO | ELIMINADO

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_persona", foreignKey = @ForeignKey(name = "fk_administrador_es_una_persona"))
    private Persona persona;

    @PrePersist
    public void establecerDefectos() {
        this.setEstadoAdmin("ACTIVO");
    }
}