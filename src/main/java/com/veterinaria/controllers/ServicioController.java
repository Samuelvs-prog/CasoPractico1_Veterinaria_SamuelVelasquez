package com.veterinaria.controllers;

import com.veterinaria.domain.Servicio;
import com.veterinaria.domain.TipoServicio;
import com.veterinaria.service.ServicioService;
import com.veterinaria.service.TipoServicioService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/servicios")
public class ServicioController {

    private final ServicioService servicioService;
    private final TipoServicioService tipoService;

    public ServicioController(ServicioService servicioService, TipoServicioService tipoService) {
        this.servicioService = servicioService;
        this.tipoService = tipoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("servicios", servicioService.listar());
        return "servicios/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Servicio s = new Servicio();
        s.setTipo(new TipoServicio());
        model.addAttribute("servicio", s);
        model.addAttribute("tipos", tipoService.listar());
        return "servicios/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        return servicioService.buscarPorId(id).map(s -> {
            model.addAttribute("servicio", s);
            model.addAttribute("tipos", tipoService.listar());
            return "servicios/form";
        }).orElseGet(() -> {
            redirect.addFlashAttribute("mensaje", "El servicio no existe.");
            redirect.addFlashAttribute("tipoMensaje", "error");
            return "redirect:/servicios";
        });
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("servicio") Servicio servicio, RedirectAttributes redirect) {
        boolean esNuevo = servicio.getId() == null;
        servicioService.guardar(servicio);
        redirect.addFlashAttribute("mensaje",
                esNuevo ? "Servicio agregado correctamente." : "Servicio modificado correctamente.");
        redirect.addFlashAttribute("tipoMensaje", "exito");
        return "redirect:/servicios";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            servicioService.eliminar(id);
            redirect.addFlashAttribute("mensaje", "Servicio eliminado correctamente.");
            redirect.addFlashAttribute("tipoMensaje", "exito");
        } catch (DataIntegrityViolationException e) {
            redirect.addFlashAttribute("mensaje", "No se puede eliminar: el servicio tiene citas asociadas.");
            redirect.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/servicios";
    }
}
