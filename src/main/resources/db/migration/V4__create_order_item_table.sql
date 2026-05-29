CREATE TABLE order_item (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    order_id BIGINT UNSIGNED NOT NULL,
    product_id BIGINT UNSIGNED NOT NULL,
    unit_price DOUBLE  NOT NULL,
    quantity BIGINT NOT NULL DEFAULT 1,
    active BOOLEAN DEFAULT TRUE,
    deleted BOOLEAN DEFAULT FALSE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at DATETIME NULL,
    CONSTRAINT pk_order_item PRIMARY KEY (id),
    CONSTRAINT fk_order_item_on_order FOREIGN KEY (order_id) REFERENCES orders (id),
    CONSTRAINT fk_order_item_on_product FOREIGN KEY (product_id) REFERENCES products (id)
);
