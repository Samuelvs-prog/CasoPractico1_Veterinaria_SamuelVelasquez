package com.veterinaria.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String mascota;

    @Column(nullable = false, length = 100)
    private String propietario;

    @Column(nullable = false)
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicio servicio;

    public Cita() {}

    public Cita(String mascota, String propietario, LocalDate fecha, Servicio servicio) {
        this.mascota = mascota;
        this.propietario = propietario;
        this.fecha = fecha;
        this.servicio = servicio;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMascota() { return mascota; }
    public void setMascota(String mascota) { this.mascota = mascota; }
    public String getPropietario() { return propietario; }
    public void setPropietario(String propietario) { this.propietario = propietario; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public Servicio getServicio() { return servicio; }
    public void setServicio(Servicio servicio) { this.servicio = servicio; }
}
