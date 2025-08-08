CREATE DATABASE IF NOT EXISTS health_mood DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE health_mood;
CREATE TABLE `cart` (
                        `cart_id` int NOT NULL AUTO_INCREMENT,
                        `customer_id` int DEFAULT NULL,
                        `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (`cart_id`),
                        KEY `idx_cart_customer_id` (`customer_id`),
                        CONSTRAINT `fk_cart_customers` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `cart_items` (
                              `cart_item_id` int NOT NULL AUTO_INCREMENT,
                              `cart_id` int DEFAULT NULL,
                              `product_id` int DEFAULT NULL,
                              `quantity` int NOT NULL,
                              PRIMARY KEY (`cart_item_id`),
                              KEY `idx_cart_items_cart_id` (`cart_id`),
                              KEY `idx_cart_items_product_id` (`product_id`),
                              CONSTRAINT `fk_cart_items_cart` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                              CONSTRAINT `fk_cart_items_products` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `categories` (
                              `category_id` int NOT NULL AUTO_INCREMENT,
                              `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                              `description` text COLLATE utf8mb4_unicode_ci,
                              PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `chatbot_logs` (
                                `log_id` int NOT NULL AUTO_INCREMENT,
                                `customer_id` int DEFAULT NULL,
                                `message` text COLLATE utf8mb4_unicode_ci NOT NULL,
                                `response` text COLLATE utf8mb4_unicode_ci,
                                `timestamp` datetime DEFAULT CURRENT_TIMESTAMP,
                                PRIMARY KEY (`log_id`),
                                KEY `idx_chatbot_logs_customer_id` (`customer_id`),
                                CONSTRAINT `fk_chatbot_logs_customers` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `comments` (
                            `comment_id` int NOT NULL AUTO_INCREMENT,
                            `post_id` int DEFAULT NULL,
                            `customer_id` int DEFAULT NULL,
                            `comment_text` text COLLATE utf8mb4_unicode_ci NOT NULL,
                            `comment_date` datetime DEFAULT CURRENT_TIMESTAMP,
                            PRIMARY KEY (`comment_id`),
                            KEY `idx_comments_post_id` (`post_id`),
                            KEY `idx_comments_customer_id` (`customer_id`),
                            CONSTRAINT `fk_comments_customers` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE SET NULL ON UPDATE CASCADE,
                            CONSTRAINT `fk_comments_posts` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `contact_messages` (
                                    `message_id` int NOT NULL AUTO_INCREMENT,
                                    `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                                    `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                                    `subject` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                    `message` text COLLATE utf8mb4_unicode_ci NOT NULL,
                                    `sent_at` datetime DEFAULT CURRENT_TIMESTAMP,
                                    PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `customers` (
                             `customer_id` int NOT NULL AUTO_INCREMENT,
                             `first_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
                             `last_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
                             `phone` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `street` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `city` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `commune` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `password` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `rol` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `register_date` datetime DEFAULT NULL,
                             PRIMARY KEY (`customer_id`),
                             UNIQUE KEY `uq_customers_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `img` (
                       `idimg` int NOT NULL AUTO_INCREMENT,
                       `imgURL` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
                       `pedido_number` int NOT NULL,
                       `is_primary` char(1) COLLATE utf8mb4_unicode_ci NOT NULL,
                       `products_product_id` int NOT NULL,
                       PRIMARY KEY (`idimg`),
                       KEY `idx_img_products` (`products_product_id`),
                       CONSTRAINT `fk_img_products` FOREIGN KEY (`products_product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `order_items` (
                               `item_id` int NOT NULL AUTO_INCREMENT,
                               `pedido_id` int NOT NULL,
                               `product_id` int NOT NULL,
                               `quantity` int NOT NULL,
                               `list_price` decimal(10,2) NOT NULL,
                               `discount` decimal(5,2) DEFAULT '0.00',
                               PRIMARY KEY (`item_id`),
                               KEY `fk_orderitems_pedido` (`pedido_id`),
                               KEY `fk_orderitems_product` (`product_id`),
                               CONSTRAINT `fk_orderitems_pedido` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`pedido_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                               CONSTRAINT `fk_orderitems_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `payments` (
                            `payment_id` int NOT NULL AUTO_INCREMENT,
                            `customer_id` int NOT NULL,
                            `pedido_id` int NOT NULL,
                            `payment_date` date NOT NULL,
                            `amount` decimal(10,2) NOT NULL,
                            PRIMARY KEY (`payment_id`),
                            KEY `fk_payments_customer` (`customer_id`),
                            KEY `fk_payments_pedido` (`pedido_id`),
                            CONSTRAINT `fk_payments_customer` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                            CONSTRAINT `fk_payments_pedido` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`pedido_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pedidos` (
                           `pedido_id` int NOT NULL AUTO_INCREMENT,
                           `customer_id` int NOT NULL,
                           `required_date` datetime DEFAULT NULL,
                           `shipped_date` datetime NOT NULL,
                           `pedido_status` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                           `comments` text,
                           `pedido_date` datetime DEFAULT NULL,
                           PRIMARY KEY (`pedido_id`),
                           KEY `fk_pedidos_customer` (`customer_id`),
                           CONSTRAINT `fk_pedidos_customer` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `posts` (
                         `post_id` int NOT NULL AUTO_INCREMENT,
                         `title` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
                         `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
                         `author` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                         `published_date` date NOT NULL,
                         PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `products` (
                            `product_id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                            `description` text COLLATE utf8mb4_unicode_ci,
                            `price` float NOT NULL,
                            `category_id` int DEFAULT NULL,
                            PRIMARY KEY (`product_id`),
                            KEY `idx_products_category_id` (`category_id`),
                            CONSTRAINT `fk_products_categories` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;