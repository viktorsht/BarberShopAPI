CREATE TABLE days (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    status BOOLEAN DEFAULT TRUE
);

INSERT INTO days (name, status) VALUES
    ('Domingo', 1),
    ('Segunda-feira', 1),
    ('Terça-feira', 1),
    ('Quarta-feira', 1),
    ('Quinta-feira', 1),
    ('Sexta-feira', 1),
    ('Sábado', 1);

CREATE TABLE active_hours (
    id INT AUTO_INCREMENT PRIMARY KEY,
    hour_time TIME NOT NULL,
    status BOOLEAN DEFAULT TRUE
);

INSERT INTO active_hours (hour_time)
SELECT 
    TIME_FORMAT(ADDTIME('06:00:00', SEC_TO_TIME(30 * (a.a + (10 * b.a) + (100 * c.a)))), '%H:%i:%s')
FROM 
    (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a
    CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5) AS b
    CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1) AS c
WHERE 
    ADDTIME('06:00:00', SEC_TO_TIME(30 * (a.a + (10 * b.a) + (100 * c.a)))) <= '23:30:00';

CREATE TABLE services (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    duration_minutes INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

INSERT INTO services (name, duration_minutes, price) VALUES
    ('Corte de Cabelo', 60, 30.00),   
    ('Barba', 30, 20.00),              
    ('Corte e Barba', 90, 45.00),     
    ('Penteado', 30, 40.00);          

CREATE TABLE payment_methods (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    status BOOLEAN DEFAULT TRUE
);

INSERT INTO payment_methods (name) VALUES
    ('Dinheiro'),
    ('Pix'),
    ('Crédito'),
    ('Débito');

CREATE TABLE client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE barber (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE schedule (
    id INT AUTO_INCREMENT PRIMARY KEY,
    day_id INT,
    hour_id INT,
    service_id INT,
    payment_method_id INT,
    client_id INT,
    barber_id INT,
    scheduled_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (day_id) REFERENCES days(id),
    FOREIGN KEY (hour_id) REFERENCES active_hours(id),
    FOREIGN KEY (service_id) REFERENCES services(id),
    FOREIGN KEY (payment_method_id) REFERENCES payment_methods(id),
    FOREIGN KEY (client_id) REFERENCES client(id),
    FOREIGN KEY (barber_id) REFERENCES barber(id)
);



