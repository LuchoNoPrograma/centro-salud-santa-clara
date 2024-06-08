package com.uap.centrosaludsantaclara.autenticacion.configuracion;

import com.uap.centrosaludsantaclara.autenticacion.entidad.Administrador;
import com.uap.centrosaludsantaclara.autenticacion.repositorio.IAdministradorRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IAdministradorRepositorio administradorRepositorio;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrador administrador = administradorRepositorio.findByIdAdministrador(Long.parseLong(username));
        if (administrador == null) {
            throw new UsernameNotFoundException("Administrador con el identificador " + username + " no fue encontrado.");
        }
        return new UserDetailsImpl(administrador);
    }
}
