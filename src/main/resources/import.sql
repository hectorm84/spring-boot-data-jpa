/* Populate tables */
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Hector M', 'Hernandez', 'hmhernandez2001@gmail.com', '2020-08-24', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Hebert', 'Hernandez', 'hfarinas88@gmail.com', '2020-08-24', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Jacqueline', 'Gregoria', 'jacqui77@hotmail.com', '2020-08-24', '');

INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Andres', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-01', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('John', 'Doe', 'john.doe@gmail.com', '2017-08-02', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Jane', 'Doe', 'jane.doe@gmail.com', '2017-08-04', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2017-08-05', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Erich', 'Gamma', 'erich.gamma@gmail.com', '2017-08-06', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Richard', 'Helm', 'richard.helm@gmail.com', '2017-08-07', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2017-08-08', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('John', 'Vlissides', 'john.vlissides@gmail.com', '2017-08-09', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('James', 'Gosling', 'james.gosling@gmail.com', '2017-08-010', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Bruce', 'Lee', 'bruce.lee@gmail.com', '2017-08-11', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Johnny', 'Doe', 'johnny.doe@gmail.com', '2017-08-12', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('John', 'Roe', 'john.roe@gmail.com', '2017-08-13', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Jane', 'Roe', 'jane.roe@gmail.com', '2017-08-14', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Richard', 'Doe', 'richard.doe@gmail.com', '2017-08-15', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Janie', 'Doe', 'janie.doe@gmail.com', '2017-08-16', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Phillip', 'Webb', 'phillip.webb@gmail.com', '2017-08-17', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Stephane', 'Nicoll', 'stephane.nicoll@gmail.com', '2017-08-18', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Sam', 'Brannen', 'sam.brannen@gmail.com', '2017-08-19', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Juergen', 'Hoeller', 'juergen.Hoeller@gmail.com', '2017-08-20', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Janie', 'Roe', 'janie.roe@gmail.com', '2017-08-21', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('John', 'Smith', 'john.smith@gmail.com', '2017-08-22', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Joe', 'Bloggs', 'joe.bloggs@gmail.com', '2017-08-23', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('John', 'Stiles', 'john.stiles@gmail.com', '2017-08-24', '');
INSERT INTO clients (first_Name, last_Name, email, created_at, image) VALUES('Richard', 'Roe', 'stiles.roe@gmail.com', '2017-08-25', '');

/* Populate tabla productos */
INSERT INTO product (name, price, created_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO product (name, price, created_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO product (name, price, created_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO product (name, price, created_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO product (name, price, created_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO product (name, price, created_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO product (name, price, created_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());

/* Creamos algunas facturas */
INSERT INTO invoice (description, observation, client_id, created_at) VALUES('Factura equipos de oficina', null, 1, NOW());

INSERT INTO invoice_lines (quantity, invoice_id, product_id, created_at) VALUES(1, 1, 1, NOW());
INSERT INTO invoice_lines (quantity, invoice_id, product_id, created_at) VALUES(2, 1, 4, NOW());
INSERT INTO invoice_lines (quantity, invoice_id, product_id, created_at) VALUES(1, 1, 5, NOW());
INSERT INTO invoice_lines (quantity, invoice_id, product_id, created_at) VALUES(1, 1, 7, NOW());


INSERT INTO invoice (description, observation, client_id, created_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());

INSERT INTO invoice_lines (quantity, invoice_id, product_id, created_at) VALUES(3, 2, 6, NOW());
