package com.veterinaria.service.impl;

import com.veterinaria.domain.Cita;
import com.veterinaria.repository.CitaRepository;
import com.veterinaria.service.CitaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository repository;

    public CitaServiceImpl(CitaRepository repository) {
        this.repository = repository;
    }

    @Override @Transactional(readOnly = true)
    public List<Cita> listar() {
        List<Cita> citas = repository.findAllByOrderByFechaAsc();
        citas.forEach(c -> c.getServicio().getNombre()); // inicializa el servicio (lazy)
        return citas;
    }
}
