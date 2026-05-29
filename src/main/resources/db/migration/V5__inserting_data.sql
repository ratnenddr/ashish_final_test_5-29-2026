INSERT INTO users (name, email, role, active, deleted, created_at)
VALUES
('System Admin', 'admin@example.com', 'ADMIN', true, false, CURRENT_TIMESTAMP),
('John Doe', 'john@example.com', 'USER', true, false, CURRENT_TIMESTAMP),
('Jane Smith', 'jane@example.com', 'USER', true, false, CURRENT_TIMESTAMP);

INSERT INTO products (name, price, stock_quantity, active, deleted, created_at, version)
VALUES
('Gaming Laptop', 1200.00, 15, true, false, CURRENT_TIMESTAMP, 0),
('Wireless Mouse', 25.50, 50, true, false, CURRENT_TIMESTAMP, 0),
('Mechanical Keyboard', 85.00, 20, true, false, CURRENT_TIMESTAMP, 0),
('Limited Edition Cap', 45.00, 1, true, false, CURRENT_TIMESTAMP, 0),
('Vintage Camera', 450.00, 2, true, false, CURRENT_TIMESTAMP, 0);