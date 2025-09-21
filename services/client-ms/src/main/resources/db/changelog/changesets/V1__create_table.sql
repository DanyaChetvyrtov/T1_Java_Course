CREATE TABLE my_user
(
    id       BIGSERIAL PRIMARY KEY,
    login    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE
);

-- Индексы для быстрого поиска по логину и email
CREATE INDEX idx_user_login ON my_user (login);
CREATE INDEX idx_user_email ON my_user (email);

CREATE TABLE client
(
    id            BIGSERIAL PRIMARY KEY,
    user_id       BIGINT NOT NULL UNIQUE,
    client_id     VARCHAR(20),
    region_code   INT    NOT NULL,
    branch_code   INT    NOT NULL,
    first_name    VARCHAR(255),
    middle_name   VARCHAR(255),
    last_name     VARCHAR(255),
    date_of_birth DATE,
    CONSTRAINT fk_client_user FOREIGN KEY (user_id) REFERENCES my_user (id) ON DELETE CASCADE
);

-- Индекс для client_id
CREATE INDEX idx_client_clientid ON client (client_id);

CREATE TABLE document
(
    id              BIGSERIAL PRIMARY KEY,
    document_id     VARCHAR(255) NOT NULL,
    document_type   VARCHAR(50)  NOT NULL,
    document_prefix VARCHAR(50),
    document_suffix VARCHAR(50),
    client_id       BIGINT       NOT NULL,
    CONSTRAINT fk_document_client FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE
);

CREATE TABLE product
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    product_key VARCHAR(50)  NOT NULL,
    product_id  VARCHAR(255) NOT NULL UNIQUE,
    created_at  TIMESTAMP    NOT NULL DEFAULT now()
);

CREATE TABLE client_product
(
    id         BIGSERIAL PRIMARY KEY,
    client_id  BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    open_date  DATE,
    close_date DATE,
    status     VARCHAR(50),
    CONSTRAINT fk_client_product_client FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE,
    CONSTRAINT fk_client_product_product FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);

CREATE INDEX idx_client_product_client ON client_product (client_id);
CREATE INDEX idx_client_product_product ON client_product (product_id);
