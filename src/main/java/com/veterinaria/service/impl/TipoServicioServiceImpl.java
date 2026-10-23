package com.veterinaria.service.impl;

import com.veterinaria.domain.TipoServicio;
import com.veterinaria.repository.TipoServicioRepository;
import com.veterinaria.service.TipoServicioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TipoServicioServiceImpl implements TipoServicioService {

    private final TipoServicioRepository repository;

    public TipoServicioServiceImpl(TipoServicioRepository repository) {
        this.repository = repository;
    }

    @Override @Transactional(readOnly = true)
    public List<TipoServicio> listar() {
        List<TipoServicio> tipos = repository.findAll();
        tipos.forEach(t -> t.getServicios().size()); // inicializa la relación 1:N
        return tipos;
    }

    @Override @Transactional(readOnly = true)
    public Optional<TipoServicio> buscarPorId(Long id) { return repository.findById(id); }

    @Override @Transactional
    public TipoServicio guardar(TipoServicio tipo) { return repository.save(tipo); }

    @Override @Transactional
    public void eliminar(Long id) { repository.deleteById(id); repository.flush(); }
}
