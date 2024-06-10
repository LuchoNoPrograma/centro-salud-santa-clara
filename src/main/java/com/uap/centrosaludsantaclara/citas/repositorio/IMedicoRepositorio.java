package com.uap.centrosaludsantaclara.citas.repositorio;

import com.uap.centrosaludsantaclara.citas.entidad.Medico;
import com.uap.centrosaludsantaclara.citas.enums.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IMedicoRepositorio extends JpaRepository<Medico, Long> {
    @Transactional(readOnly = true)
    @Query("""
            select m from Medico m
            left join Persona p on m.idMedico = p.idPersona
            where m.especialidad = ?1
            order by p.nombres, p.materno, p.materno
            """)
    List<Medico> findAllByEspecialidadOrderByNombres(Especialidad especialidad);
}