CREATE TABLE active_hours ( id INT AUTO_INCREMENT PRIMARY KEY, hour_time TIME NOT NULL, status BOOLEAN DEFAULT TRUE );

INSERT INTO active_hours (hour_time) VALUES ('00:00:00'),
       ('00:30:00'),
       ('01:00:00'),
       ('01:30:00'),
       ('02:00:00'),
       ('02:30:00'),
       ('03:00:00'),
       ('03:30:00'),
       ('04:00:00'),
       ('04:30:00'),
       ('05:00:00'),
       ('05:30:00'),
       ('06:00:00'),
       ('06:30:00'),
       ('07:00:00'),
       ('07:30:00'),
       ('08:00:00'),
       ('08:30:00'),
       ('09:00:00'),
       ('09:30:00'),
       ('10:00:00'),
       ('10:30:00'),
       ('11:00:00'),
       ('11:30:00'),
       ('12:00:00'),
       ('12:30:00'),
       ('13:00:00'),
       ('13:30:00'),
       ('14:00:00'),
       ('14:30:00'),
       ('15:00:00'),
       ('15:30:00'),
       ('16:00:00'),
       ('16:30:00'),
       ('17:00:00'),
       ('17:30:00'),
       ('18:00:00'),
       ('18:30:00'),
       ('19:00:00'),
       ('19:30:00'),
       ('20:00:00'),
       ('20:30:00'),
       ('21:00:00'),
       ('21:30:00'),
       ('22:00:00'),
       ('22:30:00'),
       ('23:00:00'),
       ('23:30:00');

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



