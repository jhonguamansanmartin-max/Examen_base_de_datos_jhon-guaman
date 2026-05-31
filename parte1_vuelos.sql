CREATE TABLE vuelos (
    id                  SERIAL PRIMARY KEY,
    codigo              VARCHAR(10)    NOT NULL UNIQUE,
    precio_boleto       NUMERIC(10,2)  NOT NULL CHECK (precio_boleto >= 0),
    asientos_disponibles INTEGER       NOT NULL CHECK (asientos_disponibles >= 0)
);


INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES ('AA-101', 250.00, 45);
INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES ('AA-202', 310.50, 3);
INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES ('LA-301', 180.75, 0);
INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES ('LA-402', 420.00, 22);
INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES ('IB-501', 560.00, 10);
INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES ('IB-602', 95.99,  0);
INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES ('KL-701', 730.00, 4);
INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES ('KL-802', 615.25, 18);
INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES ('AM-901', 140.00, 2);
INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES ('AM-110', 285.00, 35);
INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES ('AV-111', 199.99, 0);
INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES ('AV-222', 320.00, 50);
INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES ('CM-333', 455.00, 1);
INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES ('CM-444', 510.75, 28);
INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES ('UA-555', 675.00, 7);
INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES ('UA-666', 390.00, 0);
INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES ('BA-777', 820.50, 15);
INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES ('BA-888', 215.00, 3);
INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES ('AF-999', 490.00, 42);
INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES ('AF-120', 355.00, 0);

-- ── 1.3 CONSULTAS REQUERIDAS ──────────────────────────

-- 1. Alerta de Vuelo Lleno: vuelos con menos de 5 asientos disponibles
SELECT * 
FROM vuelos 
WHERE asientos_disponibles < 5;

-- 2. Incremento de Tarifas: incrementar precio 15% para vuelo con id = 1
UPDATE vuelos 
SET precio_boleto = precio_boleto * 1.15 
WHERE id = 1;

-- 3. Depuración de Rutas Canceladas: eliminar vuelos con 0 asientos
DELETE FROM vuelos 
WHERE asientos_disponibles = 0;