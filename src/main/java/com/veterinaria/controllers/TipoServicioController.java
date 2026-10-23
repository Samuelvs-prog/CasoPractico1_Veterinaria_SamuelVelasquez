package com.veterinaria.controllers;

import com.veterinaria.domain.TipoServicio;
import com.veterinaria.service.TipoServicioService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tipos")
public class TipoServicioController {

    private final TipoServicioService service;

    public TipoServicioController(TipoServicioService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tipos", service.listar());
        return "tipos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("tipo", new TipoServicio());
        return "tipos/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        return service.buscarPorId(id).map(t -> {
            model.addAttribute("tipo", t);
            return "tipos/form";
        }).orElseGet(() -> {
            redirect.addFlashAttribute("mensaje", "El tipo de servicio no existe.");
            redirect.addFlashAttribute("tipoMensaje", "error");
            return "redirect:/tipos";
        });
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("tipo") TipoServicio tipo, RedirectAttributes redirect) {
        boolean esNuevo = tipo.getId() == null;
        service.guardar(tipo);
        redirect.addFlashAttribute("mensaje",
                esNuevo ? "Tipo de servicio agregado correctamente." : "Tipo de servicio modificado correctamente.");
        redirect.addFlashAttribute("tipoMensaje", "exito");
        return "redirect:/tipos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            service.eliminar(id);
            redirect.addFlashAttribute("mensaje", "Tipo de servicio eliminado correctamente.");
            redirect.addFlashAttribute("tipoMensaje", "exito");
        } catch (DataIntegrityViolationException e) {
            redirect.addFlashAttribute("mensaje",
                    "No se puede eliminar: el tipo tiene servicios asociados.");
            redirect.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/tipos";
    }
}
