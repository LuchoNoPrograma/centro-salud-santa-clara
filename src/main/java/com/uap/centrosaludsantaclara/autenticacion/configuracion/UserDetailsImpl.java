package com.uap.centrosaludsantaclara.autenticacion.configuracion;

import com.uap.centrosaludsantaclara.autenticacion.entidad.Administrador;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private Administrador administrador;

    public UserDetailsImpl(Administrador administrador) {
        this.administrador = administrador;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return administrador.getCodigoAcceso();
    }

    @Override
    public String getUsername() {
        return administrador.getIdAdministrador().toString();
    }

    @Override
    public boolean isEnabled() {
        return administrador.getEstadoAdmin().equals("ACTIVO");
    }
}
