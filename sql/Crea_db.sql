-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema db_delivery
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_delivery
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_delivery` DEFAULT CHARACTER SET utf8 ;
USE `db_delivery` ;

-- -----------------------------------------------------
-- Table `db_delivery`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_delivery`.`users` (
  `id` INT NOT NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `rol` ENUM('Client', 'Restaurant', 'Dealer') NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_delivery`.`restaurants`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_delivery`.`restaurants` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `adress` VARCHAR(45) NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_restaurants_users_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_restaurants_users`
    FOREIGN KEY (`users_id`)
    REFERENCES `db_delivery`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_delivery`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_delivery`.`products` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `price` DOUBLE NULL,
  `Restaurants_idRestaurants` INT NOT NULL,
  `restaurants_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Restaurants_idRestaurants`),
  INDEX `fk_products_restaurants1_idx` (`restaurants_id` ASC) VISIBLE,
  CONSTRAINT `fk_products_restaurants1`
    FOREIGN KEY (`restaurants_id`)
    REFERENCES `db_delivery`.`restaurants` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_delivery`.`Client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_delivery`.`Client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `adress` VARCHAR(45) NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Client_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_Client_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `db_delivery`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_delivery`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_delivery`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NULL,
  `status` ENUM('Confirmed', 'Cancel') NULL,
  `Client_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_orders_Client1_idx` (`Client_id` ASC) VISIBLE,
  CONSTRAINT `fk_orders_Client1`
    FOREIGN KEY (`Client_id`)
    REFERENCES `db_delivery`.`Client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_delivery`.`dealer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_delivery`.`dealer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_dealer_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_dealer_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `db_delivery`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_delivery`.`delivery`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_delivery`.`delivery` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` ENUM('Delivered', 'In progress') NULL,
  `dealer_id` INT NOT NULL,
  `orders_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_delivery_dealer1_idx` (`dealer_id` ASC) VISIBLE,
  INDEX `fk_delivery_orders1_idx` (`orders_id` ASC) VISIBLE,
  CONSTRAINT `fk_delivery_dealer1`
    FOREIGN KEY (`dealer_id`)
    REFERENCES `db_delivery`.`dealer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_delivery_orders1`
    FOREIGN KEY (`orders_id`)
    REFERENCES `db_delivery`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_delivery`.`order_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_delivery`.`order_items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantity` INT NULL,
  `products_id` INT NOT NULL,
  `products_Restaurants_idRestaurants` INT NOT NULL,
  `orders_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_items_products1_idx` (`products_id` ASC, `products_Restaurants_idRestaurants` ASC) VISIBLE,
  INDEX `fk_order_items_orders1_idx` (`orders_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_items_products1`
    FOREIGN KEY (`products_id` , `products_Restaurants_idRestaurants`)
    REFERENCES `db_delivery`.`products` (`id` , `Restaurants_idRestaurants`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_items_orders1`
    FOREIGN KEY (`orders_id`)
    REFERENCES `db_delivery`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_delivery`.`payments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_delivery`.`payments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `total_price` DOUBLE NULL,
  `payment_method` ENUM('Card', 'Cash') NULL,
  `status` ENUM('Failed', 'Confirmed') NULL,
  `order_items_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_payments_order_items1_idx` (`order_items_id` ASC) VISIBLE,
  CONSTRAINT `fk_payments_order_items1`
    FOREIGN KEY (`order_items_id`)
    REFERENCES `db_delivery`.`order_items` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
