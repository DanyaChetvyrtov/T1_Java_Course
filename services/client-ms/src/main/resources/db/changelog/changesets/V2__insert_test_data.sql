INSERT INTO client_db.client_dev.my_user (login, password, email)
VALUES
    ('john_doe', 'password123', 'john.doe@example.com'),
    ('jane_smith', 'password456', 'jane.smith@example.com'),
    ('alex_kim', 'password789', 'alex.kim@example.com');

INSERT INTO client_db.client_dev.client (user_id, client_id, region_code, branch_code, first_name, middle_name, last_name, date_of_birth)
VALUES
    (1, '770100000001', 77, 01, 'John', 'A.', 'Doe', '1985-03-12'),
    (2, '770200000002', 77, 02, 'Jane', NULL, 'Smith', '1990-07-25'),
    (3, '780100000003', 78, 01, 'Alex', 'B.', 'Kim', '1995-11-05');

INSERT INTO client_db.client_dev.document (document_id, document_type, document_prefix, document_suffix, client_id)
VALUES
    ('1234 567890', 'PASSPORT', '1234', '567890', 1),
    ('1111 222333', 'PASSPORT', '1111', '222333', 2),
    ('AB1234567', 'INTERNATIONAL_PASSPORT', 'AB', '1234567', 3),
    ('XY9876543', 'DRIVER_LICENSE', 'XY', '9876543', 1);

INSERT INTO client_db.client_dev.product (name, product_key, product_id, created_at)
VALUES
    ('Credit Card', 'CARD', 'CARD1', now()),
    ('Mortgage Loan', 'LOAN', 'LOAN2', now()),
    ('Savings Account', 'ACC', 'ACC3', now());

INSERT INTO client_db.client_dev.client_product (client_id, product_id, open_date, close_date, status)
VALUES
    (1, 1, '2020-01-01', NULL, 'ACTIVE'),
    (1, 2, '2021-05-15', NULL, 'ACTIVE'),
    (2, 3, '2022-02-20', NULL, 'ACTIVE'),
    (3, 1, '2023-03-10', NULL, 'BLOCKED');
