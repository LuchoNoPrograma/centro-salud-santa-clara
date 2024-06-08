package com.uap.centrosaludsantaclara.autenticacion.repositorio;

import com.uap.centrosaludsantaclara.autenticacion.entidad.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface IPersonaRepositorio extends JpaRepository<Persona, Long> {

    Persona findByCi(@NonNull String ci);
}