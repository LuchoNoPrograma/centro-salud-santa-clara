package com.uap.centrosaludsantaclara.citas.controlador;

import com.uap.centrosaludsantaclara.citas.enums.Especialidad;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DTO for {@link com.uap.centrosaludsantaclara.citas.entidad.Cita}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AgendarCitaDto implements Serializable {
    private Long idCita;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;
    private Long pacientePersonaIdPersona;
    private Long medicoPersonaIdPersona;
    private Especialidad especialidad;
}