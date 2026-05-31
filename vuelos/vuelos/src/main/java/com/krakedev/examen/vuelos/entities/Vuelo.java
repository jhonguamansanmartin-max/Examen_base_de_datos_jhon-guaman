package com.krakedev.examen.vuelos.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

// @Entity: le dice a JPA que esta clase representa una tabla en la BD
// @Table:  indica el nombre exacto de la tabla en PostgreSQL
@Entity
@Table(name = "vuelos")
public class Vuelo {

    // @Id: esta es la llave primaria
    // @GeneratedValue: PostgreSQL la genera automáticamente (SERIAL)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @Column: mapea el atributo Java con la columna de la tabla
    @Column(name = "codigo", length = 10, nullable = false, unique = true)
    private String codigo;

    @Column(name = "precio_boleto", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioBoleto;

    @Column(name = "asientos_disponibles", nullable = false)
    private Integer asientosDisponibles;

    // ── PARTE 3: nuevo campo destino ──────────────────
    @Column(name = "destino", length = 100)
    private String destino;

    // ── Constructor vacío obligatorio para JPA ────────
    public Vuelo() {}

    // ── Getters y Setters ─────────────────────────────
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getPrecioBoleto() {
        return precioBoleto;
    }

    public void setPrecioBoleto(BigDecimal precioBoleto) {
        this.precioBoleto = precioBoleto;
    }

    public Integer getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public void setAsientosDisponibles(Integer asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}