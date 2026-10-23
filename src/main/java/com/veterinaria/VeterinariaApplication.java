package com.veterinaria;

import com.veterinaria.domain.Cita;
import com.veterinaria.domain.Servicio;
import com.veterinaria.domain.TipoServicio;
import com.veterinaria.repository.CitaRepository;
import com.veterinaria.repository.ServicioRepository;
import com.veterinaria.repository.TipoServicioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class VeterinariaApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeterinariaApplication.class, args);
    }

    /** Carga datos de ejemplo solo si la base de datos está vacía. */
    @Bean
    CommandLineRunner datosIniciales(TipoServicioRepository tipos, ServicioRepository servicios, CitaRepository citas) {
        return args -> {
            if (tipos.count() > 0) return;

            TipoServicio consulta = nuevoTipo(tipos, "Consulta", "Atención médica general");
            TipoServicio vacuna = nuevoTipo(tipos, "Vacunación", "Planes de inmunización");
            TipoServicio cirugia = nuevoTipo(tipos, "Cirugía", "Procedimientos quirúrgicos");
            nuevoTipo(tipos, "Estética", "Baño, corte y spa");

            Servicio s1 = nuevoServicio(servicios, "Consulta general", "5000", 30, consulta);
            Servicio s2 = nuevoServicio(servicios, "Vacuna antirrábica", "8500", 15, vacuna);
            Servicio s3 = nuevoServicio(servicios, "Vacuna múltiple", "12000", 20, vacuna);
            Servicio s4 = nuevoServicio(servicios, "Esterilización", "45000", 90, cirugia);

            LocalDate hoy = LocalDate.now();
            citas.save(new Cita("Max", "Carlos Rojas", hoy.plusDays(1), s1));
            citas.save(new Cita("Luna", "María Jiménez", hoy.plusDays(2), s2));
            citas.save(new Cita("Rocky", "Andrés Vargas", hoy.plusDays(3), s3));
            citas.save(new Cita("Nala", "Sofía Mora", hoy.plusDays(5), s4));
        };
    }

    private TipoServicio nuevoTipo(TipoServicioRepository r, String nombre, String desc) {
        TipoServicio t = new TipoServicio();
        t.setNombre(nombre);
        t.setDescripcion(desc);
        return r.save(t);
    }

    private Servicio nuevoServicio(ServicioRepository r, String nombre, String precio, int min, TipoServicio tipo) {
        Servicio s = new Servicio();
        s.setNombre(nombre);
        s.setPrecio(new BigDecimal(precio));
        s.setDuracionMinutos(min);
        s.setTipo(tipo);
        return r.save(s);
    }
}
