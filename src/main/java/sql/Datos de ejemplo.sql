USE `db_delivery`;

-- Insertar usuarios
INSERT INTO users (id, username, password, rol) VALUES
(1, 'admin1', 'admin123', 'Admin'),
(2, 'client1', 'pass123', 'Client'),
(3, 'restaurant1', 'pass123', 'Restaurant'),
(4, 'dealer1', 'pass123', 'Dealer');

-- Insertar restaurante
INSERT INTO restaurants (name, adress, users_id) VALUES
('Pizzeria Bella', 'Calle Falsa 123', 2);

-- Insertar cliente
INSERT INTO Client (name, last_name, phone, adress, users_id) VALUES
('Juan', 'Pérez', '123456789', 'Av. Siempreviva 742', 1);

-- Insertar dealer
INSERT INTO dealer (name, last_name, phone, users_id) VALUES
('Carlos', 'Ramírez', '987654321', 3);

-- ✅ CORREGIDO: Insertar productos (removida la columna Restaurants_idRestaurants)
INSERT INTO products (id, name, price, restaurants_id) VALUES
(1, 'Pizza Mozzarella', 10.99, 1),
(2, 'Pizza Napolitana', 12.99, 1),
(3, 'Pizza Fugazzeta', 13.99, 1);

-- Insertar orden
INSERT INTO orders (created_at, status, Client_id) VALUES
(NOW(), 'Confirmed', 1);

-- ✅ CORREGIDO: Insertar items de la orden (removida la columna products_Restaurants_idRestaurants)
INSERT INTO order_items (quantity, products_id, orders_id) VALUES
(2, 1, 1),
(1, 2, 1);

-- Insertar delivery
INSERT INTO delivery (status, dealer_id, orders_id) VALUES
('InProgress', 1, 1);

/*-- Insertar pagos
INSERT INTO payments (total_price, payment_method, status, order_items_id) VALUES
(21.98, 'Card', 'Confirmed', 1),
(12.99, 'Cash', 'Confirmed', 2);
*/