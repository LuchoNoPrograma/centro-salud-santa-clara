package com.uap.centrosaludsantaclara.autenticacion.casouso;

import com.uap.centrosaludsantaclara.autenticacion.entidad.Administrador;
import com.uap.centrosaludsantaclara.autenticacion.entidad.Persona;
import com.uap.centrosaludsantaclara.autenticacion.repositorio.IAdministradorRepositorio;
import com.uap.centrosaludsantaclara.autenticacion.repositorio.IPersonaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RegistrarseCasoUso {
    private final IAdministradorRepositorio administradorRepositorio;
    private final IPersonaRepositorio personaRepositorio;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void registrarse(Persona persona) {
        if (personaRepositorio.findByCi(persona.getCi()) != null) {
            throw new RuntimeException("La persona con el CI " + persona.getCi() + " ya se encuentra registrada");
        }

        Administrador administrador = new Administrador();
        administrador.setPersona(persona);
        administrador.setCodigoAcceso(bCryptPasswordEncoder.encode(persona.getCodigoAcceso()));
        administradorRepositorio.save(administrador);
    }
}