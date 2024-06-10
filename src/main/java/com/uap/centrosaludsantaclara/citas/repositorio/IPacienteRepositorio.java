package com.uap.centrosaludsantaclara.citas.repositorio;

import com.uap.centrosaludsantaclara.citas.entidad.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepositorio extends JpaRepository<Paciente, Long> {
}