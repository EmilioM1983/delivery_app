-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- Crear el schema
CREATE SCHEMA IF NOT EXISTS `db_delivery` DEFAULT CHARACTER SET utf8;
USE `db_delivery`;

-- Tabla: users
CREATE TABLE IF NOT EXISTS `users` (
  `id` INT NOT NULL auto_increment,
  `username` VARCHAR(45),
  `password` VARCHAR(45),
  `rol` ENUM('Client', 'Restaurant', 'Dealer', 'Admin'),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

-- Tabla: restaurants
CREATE TABLE IF NOT EXISTS `restaurants` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45),
  `adress` VARCHAR(45),
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_restaurants_users_idx` (`users_id`),
  CONSTRAINT `fk_restaurants_users`
    FOREIGN KEY (`users_id`)
    REFERENCES `users` (`id`)
) ENGINE=InnoDB;

-- ✅ Tabla: products (CORREGIDA)
CREATE TABLE IF NOT EXISTS `products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45),
  `price` DOUBLE,
  `restaurants_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_products_restaurants1_idx` (`restaurants_id`),
  CONSTRAINT `fk_products_restaurants1`
    FOREIGN KEY (`restaurants_id`)
    REFERENCES `restaurants` (`id`)
) ENGINE=InnoDB;

-- Tabla: Client
CREATE TABLE IF NOT EXISTS `Client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45),
  `last_name` VARCHAR(45),
  `phone` VARCHAR(45),
  `adress` VARCHAR(45),
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Client_users1_idx` (`users_id`),
  CONSTRAINT `fk_Client_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `users` (`id`)
) ENGINE=InnoDB;

-- Tabla: orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME,
  `status` ENUM('Confirmed', 'Cancel'),
  `Client_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_orders_Client1_idx` (`Client_id`),
  CONSTRAINT `fk_orders_Client1`
    FOREIGN KEY (`Client_id`)
    REFERENCES `Client` (`id`)
) ENGINE=InnoDB;

-- Tabla: dealer
CREATE TABLE IF NOT EXISTS `dealer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45),
  `last_name` VARCHAR(45),
  `phone` VARCHAR(45),
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_dealer_users1_idx` (`users_id`),
  CONSTRAINT `fk_dealer_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `users` (`id`)
) ENGINE=InnoDB;

-- Tabla: delivery
CREATE TABLE IF NOT EXISTS `delivery` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` ENUM('Delivered', 'InProgress'),
  `dealer_id` INT NOT NULL,
  `orders_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_delivery_dealer1_idx` (`dealer_id`),
  INDEX `fk_delivery_orders1_idx` (`orders_id`),
  CONSTRAINT `fk_delivery_dealer1`
    FOREIGN KEY (`dealer_id`)
    REFERENCES `dealer` (`id`),
  CONSTRAINT `fk_delivery_orders1`
    FOREIGN KEY (`orders_id`)
    REFERENCES `orders` (`id`)
) ENGINE=InnoDB;

-- Tabla: order_items
CREATE TABLE IF NOT EXISTS `order_items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantity` INT,
  `orders_id` INT NOT NULL,
  `products_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_items_orders1_idx` (`orders_id`),
  INDEX `fk_order_items_products1_idx` (`products_id`),
  CONSTRAINT `fk_order_items_orders1`
    FOREIGN KEY (`orders_id`)
    REFERENCES `orders` (`id`),
  CONSTRAINT `fk_order_items_products1`
    FOREIGN KEY (`products_id`)
    REFERENCES `products` (`id`)
) ENGINE=InnoDB;

-- Tabla: payments
CREATE TABLE IF NOT EXISTS `payments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `total_price` DOUBLE,
  `payment_method` ENUM('Card', 'Cash'),
  `status` ENUM('Failed', 'Confirmed'),
  `order_items_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_payments_order_items1_idx` (`order_items_id`),
  CONSTRAINT `fk_payments_order_items1`
    FOREIGN KEY (`order_items_id`)
    REFERENCES `order_items` (`id`)
) ENGINE=InnoDB;

-- Restaurar configuración original
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;




