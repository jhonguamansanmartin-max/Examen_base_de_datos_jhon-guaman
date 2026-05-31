-- =====================================================
-- PARTE 4: RELACIONES MUCHOS A MUCHOS
-- =====================================================

-- ── TABLAS MAESTRAS ───────────────────────────────────

CREATE TABLE proyectos (
    id              SERIAL PRIMARY KEY,
    nombre          VARCHAR(100) NOT NULL,
    dias_estimados  INTEGER      NOT NULL CHECK (dias_estimados > 0)
);

CREATE TABLE tecnologias (
    id        SERIAL PRIMARY KEY,
    nombre    VARCHAR(50) NOT NULL,
    categoria VARCHAR(30) NOT NULL
);

-- ── TABLA INTERMEDIA (ROMPIMIENTO) ────────────────────
-- Un proyecto puede tener MUCHAS tecnologias
-- Una tecnologia puede estar en MUCHOS proyectos
-- La PK compuesta evita que se repita la misma combinacion
CREATE TABLE proyectos_tecnologias (
    id_proyecto   INTEGER NOT NULL,
    id_tecnologia INTEGER NOT NULL,

    -- Llave primaria compuesta: evita duplicados
    PRIMARY KEY (id_proyecto, id_tecnologia),

    -- Llave foránea hacia proyectos
    CONSTRAINT fk_proyecto
        FOREIGN KEY (id_proyecto)
        REFERENCES proyectos(id)
        ON DELETE CASCADE,

    -- Llave foránea hacia tecnologias
    CONSTRAINT fk_tecnologia
        FOREIGN KEY (id_tecnologia)
        REFERENCES tecnologias(id)
        ON DELETE CASCADE
);

-- ── DATOS DE EJEMPLO ──────────────────────────────────

INSERT INTO proyectos (nombre, dias_estimados) VALUES
    ('CRM Empresarial', 120),
    ('App Delivery',     60),
    ('Portal Web',       45);

INSERT INTO tecnologias (nombre, categoria) VALUES
    ('Java',        'Backend'),
    ('Spring Boot', 'Backend'),
    ('React',       'Frontend'),
    ('PostgreSQL',  'Base de Datos'),
    ('Docker',      'DevOps');

-- Asignaciones proyecto-tecnología
INSERT INTO proyectos_tecnologias VALUES (1, 1); -- CRM usa Java
INSERT INTO proyectos_tecnologias VALUES (1, 2); -- CRM usa Spring Boot
INSERT INTO proyectos_tecnologias VALUES (1, 4); -- CRM usa PostgreSQL
INSERT INTO proyectos_tecnologias VALUES (2, 2); -- Delivery usa Spring Boot
INSERT INTO proyectos_tecnologias VALUES (2, 3); -- Delivery usa React
INSERT INTO proyectos_tecnologias VALUES (2, 5); -- Delivery usa Docker
INSERT INTO proyectos_tecnologias VALUES (3, 3); -- Portal usa React
INSERT INTO proyectos_tecnologias VALUES (3, 4); -- Portal usa PostgreSQL

-- ── CONSULTAS CON JOIN ────────────────────────────────

-- 1. Tecnologías de un proyecto específico (por nombre del proyecto)
SELECT t.nombre, t.categoria
FROM tecnologias t
JOIN proyectos_tecnologias pt ON t.id = pt.id_tecnologia
JOIN proyectos p              ON p.id = pt.id_proyecto
WHERE p.nombre = 'CRM Empresarial';

-- 2. Proyectos que usan una tecnología específica (por id)
SELECT p.nombre, p.dias_estimados
FROM proyectos p
JOIN proyectos_tecnologias pt ON p.id = pt.id_proyecto
JOIN tecnologias t            ON t.id = pt.id_tecnologia
WHERE t.id = 2; -- Spring Boot

-- 3. Reporte: tecnología y cuántos proyectos la usan (de mayor a menor)
SELECT t.nombre, COUNT(pt.id_proyecto) AS total_proyectos
FROM tecnologias t
JOIN proyectos_tecnologias pt ON t.id = pt.id_tecnologia
GROUP BY t.nombre
ORDER BY total_proyectos DESC;