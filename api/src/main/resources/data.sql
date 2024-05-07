INSERT INTO Category (name) VALUES
('Electronics'),
('Clothing'),
('Home Appliances');

INSERT INTO Address (number, road, city, add_info) VALUES
('123', 'Main Street', 'Cityville', NULL),
('456', 'Broadway', 'Townsville', 'Near the Park'),
('789', 'Elm Avenue', 'Villageton', 'Opposite the School');

INSERT INTO Shop (name, description, active_since, disable_since, siret, address_id) VALUES
('Electronics Superstore', 'Large selection of electronic gadgets', '2024-05-03 12:00:00', NULL, '123456789', 1),
('Fashion Boutique', 'Trendy clothing for all ages', '2024-05-03 10:00:00', NULL, '987654321', 2),
('Home Appliances Store', 'Appliances for kitchen and home', '2024-05-03 09:00:00', NULL, '456123789', 3);

INSERT INTO Delivery_Person (firstname, lastname, vehicle, date_of_birth, active_since, disable_since, address_id) VALUES
('John', 'Smith', 'Van', '1990-05-03', '2024-05-03 08:00:00', NULL, 2),
('Emily', 'Johnson', 'Truck', '1985-10-15', '2024-05-03 07:00:00', NULL, 3),
('Michael', 'Brown', 'Bicycle', '1992-02-20', '2024-05-03 06:00:00', NULL, 1);

INSERT INTO Admin (description) VALUES
('System Administrator'),
('Sales Manager'),
('HR Manager');

INSERT INTO Client (id, firstname, lastname, active_since, disable_since, date_of_birth) VALUES
(1, 'Alice', 'Jones', '2024-05-03 15:00:00', NULL, '1995-08-10'),
(2, 'Bob', 'Smith', '2024-05-03 14:00:00', NULL, '1992-03-25'),
(3, 'Emma', 'Davis', '2024-05-03 13:00:00', NULL, '1988-11-30');

INSERT INTO client_address (address_id, client_id) VALUES
(1, 1),
(2, 2),
(3, 3);

INSERT INTO category_shop (shop_id, category_id) VALUES
(1, 1),
(2, 2),
(3, 3);

INSERT INTO _User (mail, password, client_id, shop_id, delivery_person_id, admin_id) VALUES
('alice@example.com', 'password123', 1, NULL, NULL, NULL),
('bob@example.com', 'password456', NULL, 2, NULL, NULL),
('emily@example.com', 'password789', NULL, NULL, 1, NULL);

INSERT INTO Delivery (fee, arrive_at, got, prepared, accepted, delivered, disable, delivery_person_id) VALUES
(10.5, '2024-05-03 14:00:00', TRUE, TRUE, FALSE, FALSE, FALSE, 1),
(8.75, '2024-05-03 15:00:00', TRUE, TRUE, TRUE, TRUE, FALSE, 2),
(12.0, '2024-05-03 16:00:00', FALSE, FALSE, FALSE, FALSE, FALSE, 3);

INSERT INTO Product (name, unit_price, description, stock, active_since, disable_since, weight, sizes, for_adults, shop_id) VALUES
('Laptop', 999.99, 'High-performance laptop', 50, '2024-05-03 10:00:00', NULL, 2.5, 15.0, 1, 1),
('T-Shirt', 29.99, 'Cotton T-shirt', 100, '2024-05-03 09:00:00', NULL, 0.3, 0.5, 1, 2),
('Microwave', 149.99, 'Compact microwave oven', 20, '2024-05-03 11:00:00', NULL, 12.0, 0.5, 0, 3);

INSERT INTO _Order (order_at, payed, client_id, delivery_id, ship_to, charge_to) VALUES
('2024-05-03 10:30:00', TRUE, 1, 1, 1, 1),
('2024-05-03 11:45:00', TRUE, 2, 2, 2, 2),
('2024-05-03 12:15:00', TRUE, 3, 3, 3, 3);

INSERT INTO product_order (quantity, order_id, product_id) VALUES
(2, 1, 1),
(3, 2, 2),
(1, 3, 3);
