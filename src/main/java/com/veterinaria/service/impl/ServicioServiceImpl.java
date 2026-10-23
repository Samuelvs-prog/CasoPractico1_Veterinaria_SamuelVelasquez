package com.veterinaria.service.impl;

import com.veterinaria.domain.Servicio;
import com.veterinaria.repository.ServicioRepository;
import com.veterinaria.service.ServicioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository repository;

    public ServicioServiceImpl(ServicioRepository repository) {
        this.repository = repository;
    }

    @Override @Transactional(readOnly = true)
    public List<Servicio> listar() {
        List<Servicio> lista = repository.findAll();
        lista.forEach(s -> s.getTipo().getNombre()); // inicializa el tipo (lazy)
        return lista;
    }

    @Override @Transactional(readOnly = true)
    public Optional<Servicio> buscarPorId(Long id) { return repository.findById(id); }

    @Override @Transactional
    public Servicio guardar(Servicio servicio) { return repository.save(servicio); }

    @Override @Transactional
    public void eliminar(Long id) { repository.deleteById(id); repository.flush(); }
}
