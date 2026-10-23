package com.veterinaria.repository;

import com.veterinaria.domain.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
}
