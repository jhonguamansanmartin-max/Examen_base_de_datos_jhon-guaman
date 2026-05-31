package com.krakedev.examen.vuelos.services;

import com.krakedev.examen.vuelos.entities.Vuelo;
import com.krakedev.examen.vuelos.repositories.VueloRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

// @Service: marca esta clase como componente de lógica de negocio
// Spring la detecta automáticamente y la registra en el contenedor
@Service
public class VueloService {

    private final VueloRepository vueloRepository;

    // Inyección por constructor — sin usar new
    public VueloService(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    // ── CREAR ─────────────────────────────────────────────────────────────────
    // .save() de JpaRepository: si el objeto no tiene ID, hace INSERT
    public Vuelo crear(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    // ── LISTAR TODOS ──────────────────────────────────────────────────────────
    public List<Vuelo> listar() {
        return vueloRepository.findAll();
    }

    // ── BUSCAR POR ID ─────────────────────────────────────────────────────────
    // Optional: puede contener un Vuelo o estar vacío (si no existe el ID)
    public Optional<Vuelo> buscarPorId(Integer id) {
        return vueloRepository.findById(id);
    }

    // ── ACTUALIZAR ────────────────────────────────────────────────────────────
    // .save() con ID existente hace UPDATE
    public Optional<Vuelo> actualizar(Integer id, Vuelo datosNuevos) {
        Optional<Vuelo> optional = vueloRepository.findById(id);

        if (optional.isPresent()) {
            Vuelo existente = optional.get();
            existente.setCodigo(datosNuevos.getCodigo());
            existente.setPrecioBoleto(datosNuevos.getPrecioBoleto());
            existente.setAsientosDisponibles(datosNuevos.getAsientosDisponibles());
            existente.setDestino(datosNuevos.getDestino()); // PARTE 3
            return Optional.of(vueloRepository.save(existente));
        }

        return Optional.empty(); // no existe ese ID
    }

    // ── ELIMINAR ──────────────────────────────────────────────────────────────
    public boolean eliminar(Integer id) {
        if (vueloRepository.existsById(id)) {
            vueloRepository.deleteById(id);
            return true;
        }
        return false; // no existía
    }

    // ── CONSULTAS PERSONALIZADAS (Parte 2.3) ──────────────────────────────────
    public List<Vuelo> buscarPorPrecioMenorA(BigDecimal precio) {
        return vueloRepository.findByPrecioBoletoLessThan(precio);
    }

    public List<Vuelo> buscarConAsientosMayorA(Integer asientos) {
        return vueloRepository.findByAsientosDisponiblesGreaterThan(asientos);
    }
}