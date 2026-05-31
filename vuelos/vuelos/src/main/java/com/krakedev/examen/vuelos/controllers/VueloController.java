package com.krakedev.examen.vuelos.controllers;

import com.krakedev.examen.vuelos.entities.Vuelo;
import com.krakedev.examen.vuelos.services.VueloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vuelos")
public class VueloController {

    private final VueloService vueloService;

    // Inyección por constructor
    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    // ── POST /api/vuelos — Crear vuelo ────────────────────────────────────────
    // Body JSON ejemplo:
    // { "codigo":"EC-100", "precioBoleto":299.99, "asientosDisponibles":50, "destino":"Madrid" }
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Vuelo vuelo) {
        try {
            Vuelo creado = vueloService.crear(vuelo);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado); // 201
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al crear el vuelo: " + e.getMessage());       // 500
        }
    }

    // ── GET /api/vuelos — Listar todos ────────────────────────────────────────
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<Vuelo> vuelos = vueloService.listar();
            return ResponseEntity.ok(vuelos); // 200
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al listar vuelos: " + e.getMessage());        // 500
        }
    }

    // ── GET /api/vuelos/{id} — Buscar por ID ──────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            Optional<Vuelo> optional = vueloService.buscarPorId(id);

            if (optional.isPresent()) {
                return ResponseEntity.ok(optional.get()); // 200
            } else {
                return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Vuelo con id " + id + " no encontrado"); // 404
            }
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al buscar el vuelo: " + e.getMessage());     // 500
        }
    }

    // ── PUT /api/vuelos/{id} — Actualizar ─────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Integer id,
            @RequestBody Vuelo vuelo) {
        try {
            Optional<Vuelo> optional = vueloService.actualizar(id, vuelo);

            if (optional.isPresent()) {
                return ResponseEntity.ok(optional.get()); // 200
            } else {
                return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Vuelo con id " + id + " no encontrado");       // 404
            }
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Error al actualizar: " + e.getMessage());          // 400
        }
    }

    // ── DELETE /api/vuelos/{id} — Eliminar ────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            boolean eliminado = vueloService.eliminar(id);

            if (eliminado) {
                return ResponseEntity.noContent().build(); // 204
            } else {
                return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Vuelo con id " + id + " no encontrado");       // 404
            }
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al eliminar: " + e.getMessage());            // 500
        }
    }

    // ── GET /api/vuelos/precio/{max} — Consulta personalizada ────────────────
    // Buscar vuelos con precio menor al valor indicado
    @GetMapping("/precio/{max}")
    public ResponseEntity<?> buscarPorPrecio(@PathVariable BigDecimal max) {
        try {
            List<Vuelo> resultado = vueloService.buscarPorPrecioMenorA(max);
            return ResponseEntity.ok(resultado); // 200
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + e.getMessage());                        // 500
        }
    }
}