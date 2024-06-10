package com.uap.centrosaludsantaclara.citas.controlador;

import com.uap.centrosaludsantaclara.citas.casouso.CitasCasoUso;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/citas")
public class CitasControlador {
    private final CitasCasoUso citasCasoUso;

    @GetMapping("/ver-citas")
    public String verCitas(Model model) {
        model.addAttribute("listaCitas", citasCasoUso.verCitas());
        model.addAttribute("listaPersonas", citasCasoUso.verPersonasDisponibles());
        model.addAttribute("listaMedicos", citasCasoUso.verMedicosDisponibles());
        model.addAttribute("agendarCitaDto", new AgendarCitaDto());
        return "citas/ver-citas";
    }

    @PostMapping("/agendar-cita")
    public String agendarCita(AgendarCitaDto agendarCitaDto, RedirectAttributes redirectAttributes) {
        citasCasoUso.agendarCita(agendarCitaDto.getPacientePersonaIdPersona(), agendarCitaDto.getMedicoPersonaIdPersona(),
                agendarCitaDto.getFecha(), agendarCitaDto.getHora());

        redirectAttributes.addFlashAttribute("exito", "La cita fue agendada con exito!");
        return "redirect:/citas/ver-citas";
    }
}
