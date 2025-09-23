-- Продукты
INSERT INTO product_registry (client_id, account_id, product_id, interest_rate, open_date)
VALUES (1001, 2001, 3001, 12.50, '2023-01-15'),
       (1002, 2002, 3002, 10.00, '2023-05-10'),
       (1003, 2003, 3003, 7.75, '2024-02-01');

-- Платежи для первого продукта (id = 1)
INSERT INTO payment_registry (product_registry_id, payment_date, amount, interest_rate_amount, debt_amount, expired,
                              payment_expiration_date)
VALUES (1, '2023-02-15', 5000.00, 520.00, 4500.00, FALSE, '2023-02-20'),
       (1, '2023-03-15', 5000.00, 510.00, 4000.00, FALSE, '2023-03-20'),
       (1, '2023-04-15', 5000.00, 500.00, 3500.00, TRUE, '2023-04-20');

-- Платежи для второго продукта (id = 2)
INSERT INTO payment_registry (product_registry_id, payment_date, amount, interest_rate_amount, debt_amount, expired,
                              payment_expiration_date)
VALUES (2, '2023-06-10', 7000.00, 600.00, 6000.00, FALSE, '2023-06-15'),
       (2, '2023-07-10', 7000.00, 590.00, 5000.00, FALSE, '2023-07-15');

-- Платежи для третьего продукта (id = 3)
INSERT INTO payment_registry (product_registry_id, payment_date, amount, interest_rate_amount, debt_amount, expired,
                              payment_expiration_date)
VALUES (3, '2024-03-01', 8000.00, 620.00, 7000.00, FALSE, '2024-03-05');
