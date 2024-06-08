package com.uap.centrosaludsantaclara.autenticacion.repositorio;

import com.uap.centrosaludsantaclara.autenticacion.entidad.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface IAdministradorRepositorio extends JpaRepository<Administrador, Long> {

    Administrador findByIdAdministradorAndCodigoAcceso(@NonNull Long idAdministrador, @NonNull String codigoAcceso);

    Administrador findByIdAdministrador(@NonNull Long idAdministrador);
}