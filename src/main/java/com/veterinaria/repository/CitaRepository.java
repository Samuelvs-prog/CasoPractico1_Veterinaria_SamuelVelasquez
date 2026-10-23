package com.veterinaria.repository;

import com.veterinaria.domain.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findAllByOrderByFechaAsc();
}
