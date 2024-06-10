package com.uap.centrosaludsantaclara.aplicacion.config;

import com.uap.centrosaludsantaclara.autenticacion.configuracion.UserDetailsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditoriaConfig {
    @Bean
    AuditorAware<Long> auditorAware() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                Object principal = authentication.getPrincipal();
                if (principal instanceof UserDetailsImpl userDetails) {
                    Long idUsuario = userDetails.getAdministrador().getIdAdministrador();
                    return Optional.of(idUsuario);
                } else {
                    return Optional.empty();
                }
            } else {
                return Optional.empty();
            }
        };
    }
}
