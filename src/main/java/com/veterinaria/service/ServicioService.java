package com.veterinaria.service;

import com.veterinaria.domain.Servicio;
import java.util.List;
import java.util.Optional;

public interface ServicioService {
    List<Servicio> listar();
    Optional<Servicio> buscarPorId(Long id);
    Servicio guardar(Servicio servicio);
    void eliminar(Long id);
}
