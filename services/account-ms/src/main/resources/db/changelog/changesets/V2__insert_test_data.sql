-- Accounts
INSERT INTO account (client_id, product_id, balance, interest_rate, is_recalc, card_exist, status)
VALUES (1001, 'test', 15000.00, 12.50, TRUE, TRUE, 'ACTIVE'),
       (1002, 'test', 8000.00, 10.00, FALSE, FALSE, 'BLOCKED'),
       (1003, 'test', 25000.00, 8.50, TRUE, TRUE, 'ACTIVE');

-- Cards
INSERT INTO card (account_id, card_id, payment_system, status)
VALUES (1, '4111111111111111', 'VISA', 'ACTIVE'),
       (1, '5500000000000004', 'MASTERCARD', 'BLOCKED'),
       (3, '4000000000000002', 'VISA', 'ACTIVE');

-- Payments
INSERT INTO payment (account_id, payment_date, amount, is_credit, payed_at, type)
VALUES (1, '2023-01-15', 2000.00, TRUE, '2023-01-15 10:00:00', 'DEPOSIT'),
       (1, '2023-02-10', 1500.00, FALSE, '2023-02-10 12:00:00', 'WITHDRAWAL'),
       (2, '2023-03-01', 1000.00, TRUE, '2023-03-01 15:00:00', 'TRANSFER'),
       (3, '2023-03-20', 5000.00, TRUE, '2023-03-20 18:00:00', 'DEPOSIT');

-- Transactions
INSERT INTO transaction (account_id, card_id, type, amount, status, timestamp)
VALUES (1, 1, 'PURCHASE', 100.00, 'COMPLETE', '2023-01-15 10:30:00'),
       (1, 2, 'PURCHASE', 200.00, 'BLOCKED', '2023-02-10 11:00:00'),
       (2, NULL, 'TRANSFER', 500.00, 'PROCESSING', '2023-03-01 16:00:00'),
       (3, 3, 'REFUND', 150.00, 'COMPLETE', '2023-03-21 09:00:00');
