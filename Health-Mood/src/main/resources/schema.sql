-- Crear base de datos
CREATE DATABASE IF NOT EXISTS health_mood DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE health_mood;

-- =========================
-- Tabla: categories
-- =========================
CREATE TABLE IF NOT EXISTS categories (
                                          category_id INT NOT NULL AUTO_INCREMENT,
                                          name VARCHAR(100) NOT NULL,
    description TEXT NULL,
    PRIMARY KEY (category_id)
    ) ENGINE=InnoDB;

-- =========================
-- Tabla: products
-- =========================
CREATE TABLE IF NOT EXISTS products (
                                        product_id INT NOT NULL AUTO_INCREMENT,
                                        name VARCHAR(100) NOT NULL,
    description TEXT NULL,
    price FLOAT NOT NULL,
    category_id INT NULL,
    PRIMARY KEY (product_id),
    INDEX idx_products_category_id (category_id),
    CONSTRAINT fk_products_categories FOREIGN KEY (category_id)
    REFERENCES categories (category_id)
    ON DELETE SET NULL ON UPDATE CASCADE
    ) ENGINE=InnoDB;

-- =========================
-- Tabla: customers
-- =========================
CREATE TABLE IF NOT EXISTS customers (
                                         customer_id INT NOT NULL AUTO_INCREMENT,
                                         first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone VARCHAR(20) NULL,
    email VARCHAR(100) NULL,
    street VARCHAR(100) NULL,
    city VARCHAR(50) NULL,
    state VARCHAR(50) NULL,
    zip_code VARCHAR(10) NULL,
    PRIMARY KEY (customer_id),
    UNIQUE INDEX uq_customers_email (email)
    ) ENGINE=InnoDB;

-- =========================
-- Tabla: orders
-- =========================
CREATE TABLE IF NOT EXISTS pedidos (
                                       pedido_id INT AUTO_INCREMENT PRIMARY KEY,
                                       customer_id INT NOT NULL,
                                       pedido_date DATE NOT NULL,
                                       pedido_status VARCHAR(50) NOT NULL,
    comments TEXT,
    CONSTRAINT fk_pedidos_customer
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
    ON DELETE CASCADE ON UPDATE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- =========================
-- Tabla: order_items
-- =========================
CREATE TABLE IF NOT EXISTS order_items (
                                           item_id INT AUTO_INCREMENT PRIMARY KEY,
                                           pedido_id INT NOT NULL,
                                           product_id INT NOT NULL,
                                           quantity INT NOT NULL,
                                           list_price DECIMAL(10,2) NOT NULL,
    discount DECIMAL(5,2) DEFAULT 0,
    CONSTRAINT fk_orderitems_pedido
    FOREIGN KEY (pedido_id) REFERENCES pedidos(pedido_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_orderitems_product
    FOREIGN KEY (product_id) REFERENCES products(product_id)
    ON DELETE CASCADE ON UPDATE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =========================
-- Tabla: payments
-- =========================
CREATE TABLE IF NOT EXISTS payments (
                                        payment_id INT AUTO_INCREMENT PRIMARY KEY,
                                        customer_id INT NOT NULL,
                                        pedido_id INT NOT NULL,
                                        payment_date DATE NOT NULL,
                                        amount DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_payments_customer
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_payments_pedido
    FOREIGN KEY (pedido_id) REFERENCES pedidos(pedido_id)
    ON DELETE CASCADE ON UPDATE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =========================
-- Tabla: cart
-- =========================
CREATE TABLE IF NOT EXISTS cart (
                                    cart_id INT NOT NULL AUTO_INCREMENT,
                                    customer_id INT NULL,
                                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                    PRIMARY KEY (cart_id),
    INDEX idx_cart_customer_id (customer_id),
    CONSTRAINT fk_cart_customers FOREIGN KEY (customer_id)
    REFERENCES customers (customer_id)
    ON DELETE CASCADE ON UPDATE CASCADE
    ) ENGINE=InnoDB;

-- =========================
-- Tabla: cart_items
-- =========================
CREATE TABLE IF NOT EXISTS cart_items (
                                          cart_item_id INT NOT NULL AUTO_INCREMENT,
                                          cart_id INT NULL,
                                          product_id INT NULL,
                                          quantity INT NOT NULL,
                                          PRIMARY KEY (cart_item_id),
    INDEX idx_cart_items_cart_id (cart_id),
    INDEX idx_cart_items_product_id (product_id),
    CONSTRAINT fk_cart_items_cart FOREIGN KEY (cart_id)
    REFERENCES cart (cart_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_cart_items_products FOREIGN KEY (product_id)
    REFERENCES products (product_id)
    ON DELETE CASCADE ON UPDATE CASCADE
    ) ENGINE=InnoDB;

-- =========================
-- Tabla: posts
-- =========================
CREATE TABLE IF NOT EXISTS posts (
                                     post_id INT NOT NULL AUTO_INCREMENT,
                                     title VARCHAR(150) NOT NULL,
    content TEXT NOT NULL,
    author VARCHAR(100) NOT NULL,
    published_date DATE NOT NULL,
    PRIMARY KEY (post_id)
    ) ENGINE=InnoDB;

-- =========================
-- Tabla: comments
-- =========================
CREATE TABLE IF NOT EXISTS comments (
                                        comment_id INT NOT NULL AUTO_INCREMENT,
                                        post_id INT NULL,
                                        customer_id INT NULL,
                                        comment_text TEXT NOT NULL,
                                        comment_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                                        PRIMARY KEY (comment_id),
    INDEX idx_comments_post_id (post_id),
    INDEX idx_comments_customer_id (customer_id),
    CONSTRAINT fk_comments_posts FOREIGN KEY (post_id)
    REFERENCES posts (post_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_comments_customers FOREIGN KEY (customer_id)
    REFERENCES customers (customer_id)
    ON DELETE SET NULL ON UPDATE CASCADE
    ) ENGINE=InnoDB;

-- =========================
-- Tabla: contact_messages
-- =========================
CREATE TABLE IF NOT EXISTS contact_messages (
                                                message_id INT NOT NULL AUTO_INCREMENT,
                                                name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    subject VARCHAR(150) NULL,
    message TEXT NOT NULL,
    sent_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (message_id)
    ) ENGINE=InnoDB;

-- =========================
-- Tabla: chatbot_logs
-- =========================
CREATE TABLE IF NOT EXISTS chatbot_logs (
                                            log_id INT NOT NULL AUTO_INCREMENT,
                                            customer_id INT NULL,
                                            message TEXT NOT NULL,
                                            response TEXT NULL,
                                            timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
                                            PRIMARY KEY (log_id),
    INDEX idx_chatbot_logs_customer_id (customer_id),
    CONSTRAINT fk_chatbot_logs_customers FOREIGN KEY (customer_id)
    REFERENCES customers (customer_id)
    ON DELETE CASCADE ON UPDATE CASCADE
    ) ENGINE=InnoDB;

-- =========================
-- Tabla: img
-- =========================
CREATE TABLE IF NOT EXISTS img (
                                   idimg INT NOT NULL AUTO_INCREMENT,
                                   imgURL VARCHAR(150) NOT NULL,
    `pedido_number` INT NOT NULL,
    is_primary CHAR(1) NOT NULL,
    products_product_id INT NOT NULL,
    PRIMARY KEY (idimg),
    INDEX idx_img_products (products_product_id),
    CONSTRAINT fk_img_products FOREIGN KEY (products_product_id)
    REFERENCES products (product_id)
    ON DELETE CASCADE ON UPDATE CASCADE
    ) ENGINE=InnoDB;
