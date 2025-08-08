SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- =====================

-- TABLAS BASE

-- =====================

CREATE TABLE IF NOT EXISTS customers (
                                         customer_id INT AUTO_INCREMENT PRIMARY KEY,
                                         first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    street VARCHAR(100),
    city VARCHAR(50),
    commune VARCHAR(50),
    password VARCHAR(200),
    register_date DATE DEFAULT (CURRENT_DATE),
    rol VARCHAR(45)
    );

CREATE TABLE categories (

                            category_id INT AUTO_INCREMENT PRIMARY KEY,

                            name VARCHAR(50) NOT NULL

);

CREATE TABLE products (

                          product_id INT AUTO_INCREMENT PRIMARY KEY,

                          name VARCHAR(100) NOT NULL,

                          description TEXT,

                          price DECIMAL(10,2) NOT NULL,

                          stock INT DEFAULT 0,

                          category_id INT,

                          FOREIGN KEY (category_id) REFERENCES categories(category_id)

);

CREATE TABLE pedidos (

                         pedido_id INT AUTO_INCREMENT PRIMARY KEY,

                         customer_id INT,

                         fecha DATE DEFAULT (CURRENT_DATE),

                         estado VARCHAR(50),

                         total DECIMAL(10,2),

                         FOREIGN KEY (customer_id) REFERENCES customers(customer_id)

);

CREATE TABLE posts (

                       post_id INT AUTO_INCREMENT PRIMARY KEY,

                       title VARCHAR(100),

                       content TEXT

);

-- =====================

-- TABLAS DEPENDIENTES

-- =====================

CREATE TABLE cart (

                      cart_id INT AUTO_INCREMENT PRIMARY KEY,

                      customer_id INT,

                      FOREIGN KEY (customer_id) REFERENCES customers(customer_id)

);

CREATE TABLE cart_items (

                            cart_item_id INT AUTO_INCREMENT PRIMARY KEY,

                            cart_id INT,

                            product_id INT,

                            quantity INT,

                            FOREIGN KEY (cart_id) REFERENCES cart(cart_id),

                            FOREIGN KEY (product_id) REFERENCES products(product_id)

);

CREATE TABLE comments (

                          comment_id INT AUTO_INCREMENT PRIMARY KEY,

                          post_id INT,

                          customer_id INT,

                          content TEXT,

                          FOREIGN KEY (post_id) REFERENCES posts(post_id),

                          FOREIGN KEY (customer_id) REFERENCES customers(customer_id)

);

CREATE TABLE chatbot_logs (

                              log_id INT AUTO_INCREMENT PRIMARY KEY,

                              customer_id INT,

                              message TEXT,

                              FOREIGN KEY (customer_id) REFERENCES customers(customer_id)

);

CREATE TABLE payments (

                          payment_id INT AUTO_INCREMENT PRIMARY KEY,

                          customer_id INT,

                          pedido_id INT,

                          amount DECIMAL(10,2),

                          fecha DATE DEFAULT (CURRENT_DATE),

                          FOREIGN KEY (customer_id) REFERENCES customers(customer_id),

                          FOREIGN KEY (pedido_id) REFERENCES pedidos(pedido_id)

);

CREATE TABLE order_items (

                             order_item_id INT AUTO_INCREMENT PRIMARY KEY,

                             pedido_id INT,

                             product_id INT,

                             quantity INT,

                             price DECIMAL(10,2),

                             FOREIGN KEY (pedido_id) REFERENCES pedidos(pedido_id),

                             FOREIGN KEY (product_id) REFERENCES products(product_id)

);

CREATE TABLE img (

                     img_id INT AUTO_INCREMENT PRIMARY KEY,

                     product_id INT,

                     url VARCHAR(255),

                     FOREIGN KEY (product_id) REFERENCES products(product_id)

);

CREATE TABLE contact_messages (

                                  message_id INT AUTO_INCREMENT PRIMARY KEY,

                                  name VARCHAR(100),

                                  email VARCHAR(100),

                                  message TEXT

);

SET SQL_MODE=@OLD_SQL_MODE;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
-- 
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- =====================

-- TABLAS BASE

-- =====================

CREATE TABLE IF NOT EXISTS customers (
                                         customer_id INT AUTO_INCREMENT PRIMARY KEY,
                                         first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    street VARCHAR(100),
    city VARCHAR(50),
    commune VARCHAR(50),
    password VARCHAR(200),
    register_date DATE DEFAULT (CURRENT_DATE),
    rol VARCHAR(45)
    );

CREATE TABLE categories (

                            category_id INT AUTO_INCREMENT PRIMARY KEY,

                            name VARCHAR(50) NOT NULL

);

CREATE TABLE products (

                          product_id INT AUTO_INCREMENT PRIMARY KEY,

                          name VARCHAR(100) NOT NULL,

                          description TEXT,

                          price DECIMAL(10,2) NOT NULL,

                          stock INT DEFAULT 0,

                          category_id INT,

                          FOREIGN KEY (category_id) REFERENCES categories(category_id)

);

CREATE TABLE pedidos (

                         pedido_id INT AUTO_INCREMENT PRIMARY KEY,

                         customer_id INT,

                         fecha DATE DEFAULT (CURRENT_DATE),

                         estado VARCHAR(50),

                         total DECIMAL(10,2),

                         FOREIGN KEY (customer_id) REFERENCES customers(customer_id)

);

CREATE TABLE posts (

                       post_id INT AUTO_INCREMENT PRIMARY KEY,

                       title VARCHAR(100),

                       content TEXT

);

-- =====================

-- TABLAS DEPENDIENTES

-- =====================

CREATE TABLE cart (

                      cart_id INT AUTO_INCREMENT PRIMARY KEY,

                      customer_id INT,

                      FOREIGN KEY (customer_id) REFERENCES customers(customer_id)

);

CREATE TABLE cart_items (

                            cart_item_id INT AUTO_INCREMENT PRIMARY KEY,

                            cart_id INT,

                            product_id INT,

                            quantity INT,

                            FOREIGN KEY (cart_id) REFERENCES cart(cart_id),

                            FOREIGN KEY (product_id) REFERENCES products(product_id)

);

CREATE TABLE comments (

                          comment_id INT AUTO_INCREMENT PRIMARY KEY,

                          post_id INT,

                          customer_id INT,

                          content TEXT,

                          FOREIGN KEY (post_id) REFERENCES posts(post_id),

                          FOREIGN KEY (customer_id) REFERENCES customers(customer_id)

);

CREATE TABLE chatbot_logs (

                              log_id INT AUTO_INCREMENT PRIMARY KEY,

                              customer_id INT,

                              message TEXT,

                              FOREIGN KEY (customer_id) REFERENCES customers(customer_id)

);

CREATE TABLE payments (

                          payment_id INT AUTO_INCREMENT PRIMARY KEY,

                          customer_id INT,

                          pedido_id INT,

                          amount DECIMAL(10,2),

                          fecha DATE DEFAULT (CURRENT_DATE),

                          FOREIGN KEY (customer_id) REFERENCES customers(customer_id),

                          FOREIGN KEY (pedido_id) REFERENCES pedidos(pedido_id)

);

CREATE TABLE order_items (

                             order_item_id INT AUTO_INCREMENT PRIMARY KEY,

                             pedido_id INT,

                             product_id INT,

                             quantity INT,

                             price DECIMAL(10,2),

                             FOREIGN KEY (pedido_id) REFERENCES pedidos(pedido_id),

                             FOREIGN KEY (product_id) REFERENCES products(product_id)

);

CREATE TABLE img (

                     img_id INT AUTO_INCREMENT PRIMARY KEY,

                     product_id INT,

                     url VARCHAR(255),

                     FOREIGN KEY (product_id) REFERENCES products(product_id)

);

CREATE TABLE contact_messages (

                                  message_id INT AUTO_INCREMENT PRIMARY KEY,

                                  name VARCHAR(100),

                                  email VARCHAR(100),

                                  message TEXT

);

SET SQL_MODE=@OLD_SQL_MODE;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
