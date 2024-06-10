package com.uap.centrosaludsantaclara.citas.casouso;

import com.uap.centrosaludsantaclara.autenticacion.entidad.Persona;
import com.uap.centrosaludsantaclara.autenticacion.repositorio.IPersonaRepositorio;
import com.uap.centrosaludsantaclara.citas.entidad.Cita;
import com.uap.centrosaludsantaclara.citas.entidad.Medico;
import com.uap.centrosaludsantaclara.citas.entidad.Paciente;
import com.uap.centrosaludsantaclara.citas.entidad.Secretaria;
import com.uap.centrosaludsantaclara.citas.repositorio.ICitaRepositorio;
import com.uap.centrosaludsantaclara.citas.repositorio.IMedicoRepositorio;
import com.uap.centrosaludsantaclara.citas.repositorio.IPacienteRepositorio;
import com.uap.centrosaludsantaclara.citas.repositorio.ISecretariaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CitasCasoUso {
    private final IPacienteRepositorio pacienteRepositorio;
    private final ICitaRepositorio citaRepositorio;
    private final IPersonaRepositorio personaRepositorio;
    private final ISecretariaRepositorio secretariaRepositorio;
    private final IMedicoRepositorio medicoRepositorio;

    @Transactional
    public Paciente registrarPaciente(Persona persona) {
        Persona personaEncontrada = personaRepositorio.findByCi(persona.getCi());

        //Si existe persona, entonces solo registrar su ingreso como paciente
        if (personaEncontrada != null) {
            Paciente nuevoPaciente = new Paciente();
            nuevoPaciente.setPersona(personaEncontrada);
            return pacienteRepositorio.save(nuevoPaciente);
        }

        //Si no existe persona, entonces crear uno nuevo y asociar al paciente
        Persona nuevaPersonaRegistrada = personaRepositorio.save(Persona.builder()
                .nombres(persona.getNombres())
                .paterno(persona.getPaterno())
                .materno(persona.getMaterno())
                .ci(persona.getCi())
                .sexo(persona.getSexo())
                .fechaNacimiento(persona.getFechaNacimiento())
                .build());

        Paciente paciente = new Paciente();
        paciente.setPersona(nuevaPersonaRegistrada);
        return pacienteRepositorio.save(paciente);
    }

    @Transactional
    public Cita agendarCita(Long idPersona, Long idMedico, LocalDate fecha, LocalTime hora) {
        Long idSecretaria = 1L;
        Secretaria secretaria = secretariaRepositorio.getReferenceById(idSecretaria);
        Persona persona = personaRepositorio.getReferenceById(idPersona);
        Medico medico = medicoRepositorio.getReferenceById(idMedico);

        Paciente paciente = new Paciente();
        paciente.setPersona(persona);
        Cita cita = Cita.builder()
                .fecha(fecha)
                .hora(hora)
                .estado("PENDIENTE")
                .paciente(paciente)
                .secretaria(secretaria)
                .medico(medico)
                .build();

        return citaRepositorio.save(cita);
    }

    public List<Cita> verCitas(){
        return citaRepositorio.findAllOrderByFechaAndHora();
    }

    public List<Persona> verPersonasDisponibles(){
        return personaRepositorio.findAllOrderByNombres();
    }

    public List<Medico> verMedicosDisponibles(){
        return medicoRepositorio.findAllOrderByNombres();
    }

    public Cita cancelarCitaCasoUso(Long idCita){
        Cita cita = citaRepositorio.getReferenceById(idCita);
        cita.setEstado("CANCELADA");
        return citaRepositorio.save(cita);
    }
}
