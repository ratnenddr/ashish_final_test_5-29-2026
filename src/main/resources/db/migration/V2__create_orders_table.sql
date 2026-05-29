CREATE TABLE orders (
                        id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        user_id BIGINT UNSIGNED NOT NULL,
                        ordered_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                        total_amount DOUBLE,
                        active BOOLEAN DEFAULT TRUE,
                        deleted BOOLEAN DEFAULT FALSE,
                        status VARCHAR(255),
                        created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                        updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        deleted_at DATETIME NULL,
                        items TEXT,
                        CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users(id)
);
