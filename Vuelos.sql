-- Tablas maestras
CREATE TABLE proyectos (
    id             SERIAL PRIMARY KEY,
    nombre         VARCHAR(100) NOT NULL,
    dias_estimados INTEGER      NOT NULL CHECK (dias_estimados > 0)
);

CREATE TABLE tecnologias (
    id        SERIAL PRIMARY KEY,
    nombre    VARCHAR(50) NOT NULL,
    categoria VARCHAR(30) NOT NULL
);

-- Tabla intermedia
CREATE TABLE proyectos_tecnologias (
    id_proyecto   INTEGER NOT NULL,
    id_tecnologia INTEGER NOT NULL,
    PRIMARY KEY (id_proyecto, id_tecnologia),
    CONSTRAINT fk_proyecto
        FOREIGN KEY (id_proyecto)
        REFERENCES proyectos(id) ON DELETE CASCADE,
    CONSTRAINT fk_tecnologia
        FOREIGN KEY (id_tecnologia)
        REFERENCES tecnologias(id) ON DELETE CASCADE
);

-- Datos de ejemplo
INSERT INTO proyectos (nombre, dias_estimados) VALUES
    ('CRM Empresarial', 120),
    ('App Delivery', 60),
    ('Portal Web', 45);

INSERT INTO tecnologias (nombre, categoria) VALUES
    ('Java', 'Backend'),
    ('Spring Boot', 'Backend'),
    ('React', 'Frontend'),
    ('PostgreSQL', 'Base de Datos'),
    ('Docker', 'DevOps');

INSERT INTO proyectos_tecnologias VALUES (1,1),(1,2),(1,4),(2,2),(2,3),(2,5),(3,3),(3,4);

-- Consulta 1: Tecnologías de un proyecto
SELECT t.nombre, t.categoria
FROM tecnologias t
JOIN proyectos_tecnologias pt ON t.id = pt.id_tecnologia
JOIN proyectos p ON p.id = pt.id_proyecto
WHERE p.nombre = 'CRM Empresarial';

-- Consulta 2: Proyectos por tecnología
SELECT p.nombre, p.dias_estimados
FROM proyectos p
JOIN proyectos_tecnologias pt ON p.id = pt.id_proyecto
JOIN tecnologias t ON t.id = pt.id_tecnologia
WHERE t.id = 2;

-- Consulta 3: Reporte de uso tecnológico
SELECT t.nombre, COUNT(pt.id_proyecto) AS total_proyectos
FROM tecnologias t
JOIN proyectos_tecnologias pt ON t.id = pt.id_tecnologia
GROUP BY t.nombre
ORDER BY total_proyectos DESC;