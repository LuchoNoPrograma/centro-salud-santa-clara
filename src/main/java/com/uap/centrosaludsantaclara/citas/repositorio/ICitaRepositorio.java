package com.uap.centrosaludsantaclara.citas.repositorio;

import com.uap.centrosaludsantaclara.citas.entidad.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICitaRepositorio extends JpaRepository<Cita, Long> {
    @Query("select c from Cita c order by c.fecha asc, c.hora asc")
    List<Cita> findAllOrderByFechaAndHora();
}