-- Insertar datos en users
INSERT INTO users (id, username, password, rol) VALUES
(1, 'client1', 'pass123', 'Client'),
(2, 'restaurant1', 'pass123', 'Restaurant'),
(3, 'dealer1', 'pass123', 'Dealer'),
(4, 'admin1', 'adminpass', 'Admin');

-- Insertar datos en restaurants
INSERT INTO restaurants (id, name, adress, users_id) VALUES
(1, 'Pasta House', '123 Main St', 2);

-- Insertar datos en products
INSERT INTO products (id, name, price, restaurants_id) VALUES
(1, 'Spaghetti Bolognese', 10.99, 1),
(2, 'Lasagna', 12.50, 1),
(3, 'Garlic Bread', 4.00, 1);

-- Insertar datos en Client
INSERT INTO Client (id, name, last_name, phone, adress, users_id) VALUES
(1, 'Ana', 'Gomez', '123456789', '456 Oak Ave', 1);

-- Insertar datos en orders
INSERT INTO orders (id, created_at, status, Client_id) VALUES
(1, '2025-06-01 12:30:00', 'Confirmed', 1),
(2, '2025-06-01 13:00:00', 'Cancel', 1);

-- Insertar datos en dealer
INSERT INTO dealer (id, name, last_name, phone, users_id) VALUES
(1, 'Carlos', 'Pérez', '987654321', 3);

-- Insertar datos en delivery
INSERT INTO delivery (id, status, dealer_id, orders_id) VALUES
(1, 'Delivered', 1, 1),
(2, 'InProgress', 1, 2);

-- Insertar datos en order_items
INSERT INTO order_items (id, quantity, orders_id, products_id) VALUES
(1, 2, 1, 1), -- 2 Spaghetti
(2, 1, 1, 3), -- 1 Garlic Bread
(3, 1, 2, 2); -- 1 Lasagna (cancelado)

/*-- Insertar datos en payments
INSERT INTO payments (id, total_price, payment_method, status, order_items_id) VALUES
(1, 21.98, 'Card', 'Confirmed', 1),
(2, 4.00, 'Cash', 'Confirmed', 2),
(3, 12.50, 'Card', 'Failed', 3);
*/