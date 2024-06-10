package com.uap.centrosaludsantaclara.citas.controlador;

import com.uap.centrosaludsantaclara.citas.casouso.CitasCasoUso;
import com.uap.centrosaludsantaclara.citas.enums.Especialidad;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/citas")
public class CitasControlador {
    private final CitasCasoUso citasCasoUso;

    @GetMapping("/ver-citas")
    public String verCitas(Model model) {
        model.addAttribute("listaCitas", citasCasoUso.verCitas());
        model.addAttribute("listaPersonas", citasCasoUso.verPersonasDisponibles());
        model.addAttribute("listaEspecialidades", Especialidad.values());
        model.addAttribute("agendarCitaDto", new AgendarCitaDto());
        return "citas/ver-citas";
    }

    @GetMapping("/medico/especialidad/{especialidad}")
    public ResponseEntity<List<MedicoCitasDto>> listarMedicosCitas(@PathVariable Especialidad especialidad) {
        List<MedicoCitasDto> listaMedicoCitasDto = citasCasoUso.verMedicosDisponiblesPorEspecialidad(especialidad).stream().map(medico -> MedicoCitasDto.builder()
                .idMedico(medico.getIdMedico())
                .personaNombreCompleto(medico.getPersona().getNombreCompleto())
                .build()
        ).toList();

        return ResponseEntity.status(200).body(listaMedicoCitasDto);
    }

    @PostMapping("/agendar-cita")
    public String agendarCita(AgendarCitaDto agendarCitaDto, RedirectAttributes redirectAttributes) {
        citasCasoUso.agendarCita(agendarCitaDto.getPacientePersonaIdPersona(), agendarCitaDto.getMedicoPersonaIdPersona(),
                agendarCitaDto.getFecha(), agendarCitaDto.getHora());

        redirectAttributes.addFlashAttribute("exito", "La cita fue agendada con exito!");
        return "redirect:/citas/ver-citas";
    }
}
