package com.krakedev.examen.vuelos.repositories;

import com.krakedev.examen.vuelos.entities.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.util.List;

// JpaRepository<Vuelo, Integer>:
//   - Vuelo    = la entidad que maneja
//   - Integer  = el tipo del ID (@Id)
// Spring genera automáticamente: save, findById, findAll, deleteById, etc.
public interface VueloRepository extends JpaRepository<Vuelo, Integer> {

    // ── PARTE 2.3: Consulta personalizada ────────────────────────────────────
    // Spring lee el nombre del método y genera el SQL automáticamente:
    // SELECT * FROM vuelos WHERE precio_boleto < :precio
    List<Vuelo> findByPrecioBoletoLessThan(BigDecimal precio);

    // SELECT * FROM vuelos WHERE asientos_disponibles > :asientos
    List<Vuelo> findByAsientosDisponiblesGreaterThan(Integer asientos);
}