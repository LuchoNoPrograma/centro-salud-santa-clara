package com.uap.centrosaludsantaclara.autenticacion;

import com.uap.centrosaludsantaclara.autenticacion.casouso.RegistrarseCasoUso;
import com.uap.centrosaludsantaclara.autenticacion.entidad.Persona;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Log4j2
public class Iniciador {
    private final RegistrarseCasoUso registrarseCasoUso;

    private boolean inicioAplicacion = false;

    @EventListener(ApplicationReadyEvent.class)
    public void iniciarRegistros() {
        if (inicioAplicacion) {
            return;
        }

        Persona nuevaPersona = Persona.builder()
                .nombres("JESUS")
                .paterno("TANGARA")
                .materno("BERNABE")
                .ci("123456")
                .sexo('M')
                .fechaNacimiento(LocalDate.of(2001, 2, 21))
                .codigoAcceso("123456")
                .build();

        try {
            registrarseCasoUso.registrarse(nuevaPersona);
        } catch (RuntimeException exception) {
            log.error(exception.getMessage());
        }

        inicioAplicacion = true;
    }
}
