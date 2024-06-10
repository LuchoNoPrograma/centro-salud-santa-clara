package com.uap.centrosaludsantaclara.autenticacion.repositorio;

import com.uap.centrosaludsantaclara.autenticacion.entidad.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPersonaRepositorio extends JpaRepository<Persona, Long> {
    @Transactional(readOnly = true)
    @Query("select p from Persona p order by p.nombres, p.materno, p.materno")
    List<Persona> findAllOrderByNombres();

    Persona findByCi(@NonNull String ci);
}