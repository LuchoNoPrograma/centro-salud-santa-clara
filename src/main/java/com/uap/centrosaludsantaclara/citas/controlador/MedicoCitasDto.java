package com.uap.centrosaludsantaclara.citas.controlador;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.uap.centrosaludsantaclara.citas.entidad.Medico}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MedicoCitasDto implements Serializable {
    private Long idMedico;
    private String personaNombreCompleto;
}