package com.veterinaria.service;

import com.veterinaria.domain.TipoServicio;
import java.util.List;
import java.util.Optional;

public interface TipoServicioService {
    List<TipoServicio> listar();
    Optional<TipoServicio> buscarPorId(Long id);
    TipoServicio guardar(TipoServicio tipo);
    void eliminar(Long id);
}
