CREATE TABLE hours ( id INT AUTO_INCREMENT PRIMARY KEY, hour_time TIME NOT NULL, status BOOLEAN DEFAULT TRUE );

INSERT INTO hours (hour_time) VALUES ('07:00:00'),
       ('08:00:00'),
       ('09:00:00'),
       ('10:00:00'),
       ('11:00:00'),
       ('12:00:00'),
       ('13:00:00'),
       ('14:00:00'),
       ('15:00:00'),
       ('16:00:00'),
       ('17:00:00'),
       ('18:00:00'),
       ('19:00:00'),
       ('20:00:00'),
       ('21:00:00'),
       ('22:00:00');

CREATE TABLE days (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    status BOOLEAN DEFAULT TRUE
);

INSERT INTO days (name, status) VALUES
    ('Segunda-feira', 1),
    ('Terça-feira', 1),
    ('Quarta-feira', 1),
    ('Quinta-feira', 1),
    ('Sexta-feira', 1),
    ('Sábado', 1),
    ('Domingo', 1);

CREATE TABLE active_hours (
    id INT AUTO_INCREMENT PRIMARY KEY,
    day_id INT,
    hour_time TIME,
    status BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (day_id) REFERENCES days(id)
);

INSERT INTO active_hours (day_id, hour_time, status)
SELECT
    days.id AS day_id,
    hours.hour_time AS hour_time,
    CASE
        WHEN days.id IN (7) THEN FALSE
        ELSE TRUE
    END AS status
FROM
    days, hours;

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
    scheduled_day VARCHAR(26) NOT NULL,
    service_id INT,
    payment_method_id INT,
    client_id INT,
    barber_id INT,
    scheduled_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (service_id) REFERENCES services(id),
    FOREIGN KEY (payment_method_id) REFERENCES payment_methods(id),
    FOREIGN KEY (client_id) REFERENCES client(id),
    FOREIGN KEY (barber_id) REFERENCES barber(id)
);



