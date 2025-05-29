USE db_delivery;

-- Users
INSERT INTO users (id, username, password, rol) VALUES
(1, 'cliente1', 'pass123', 'Client'),
(2, 'resto1', 'pass123', 'Restaurant'),
(3, 'dealer1', 'pass123', 'Dealer'),
(4, 'admin1', 'admin123', 'Admin');

-- Clients
INSERT INTO Client (name, last_name, phone, adress, users_id) VALUES
('Juan', 'Pérez', '123456789', 'Av. Siempre Viva 742', 1);

-- Restaurants
INSERT INTO restaurants (name, adress, users_id) VALUES
('PizzaManía', 'Calle Falsa 123', 2);

-- Products
INSERT INTO products (name, price, Restaurants_idRestaurants, restaurants_id) VALUES
('Pizza Mozzarella', 1500.00, 1, 1),
('Empanadas (6)', 1000.00, 1, 1);

-- Orders
INSERT INTO orders (created_at, status, Client_id) VALUES
(NOW(), 'Confirmed', 1);

-- Dealers
INSERT INTO dealer (name, last_name, phone, users_id) VALUES
('Luis', 'Gómez', '987654321', 3);

-- Delivery
INSERT INTO delivery (status, dealer_id, orders_id) VALUES
('Delivered', 1, 1);

-- Order Items
INSERT INTO order_items (quantity, orders_id, products_id) VALUES
(2, 1, 1),
(1, 1, 2);

/*-- Payments
INSERT INTO payments (total_price, payment_method, status, order_items_id) VALUES
(3000.00, 'Card', 'Confirmed', 1),
(1000.00, 'Cash', 'Confirmed', 2);
*/