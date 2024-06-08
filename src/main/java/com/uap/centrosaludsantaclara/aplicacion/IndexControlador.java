package com.uap.centrosaludsantaclara.aplicacion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexControlador {
    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping({"/inicio", "/", "/index"})
    String index() {
        return "index";
    }
}
