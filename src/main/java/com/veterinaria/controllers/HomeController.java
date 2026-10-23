package com.veterinaria.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    // Formulario de consultas: solo interfaz, sin base de datos
    @PostMapping("/consultas")
    public String enviarConsulta(@RequestParam String nombre,
                                 @RequestParam String correo,
                                 @RequestParam String mascota,
                                 @RequestParam String mensaje,
                                 RedirectAttributes redirect) {
        redirect.addFlashAttribute("mensaje",
                "¡Gracias, " + nombre + "! Recibimos tu consulta sobre " + mascota
                + ". Te responderemos pronto a " + correo + ".");
        redirect.addFlashAttribute("tipoMensaje", "exito");
        return "redirect:/#consultas";
    }
}
