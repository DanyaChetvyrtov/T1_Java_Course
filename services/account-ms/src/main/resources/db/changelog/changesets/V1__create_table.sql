CREATE TABLE account
(
    id            BIGSERIAL PRIMARY KEY,
    client_id     BIGINT         NOT NULL,
    product_id    VARCHAR(50)    NOT NULL,
    balance       NUMERIC(15, 2) NOT NULL,
    interest_rate NUMERIC(5, 2),
    is_recalc     BOOLEAN        NOT NULL DEFAULT FALSE,
    card_exist    BOOLEAN        NOT NULL DEFAULT FALSE,
    status        VARCHAR(50)    NOT NULL
);

CREATE TABLE card
(
    id             BIGSERIAL PRIMARY KEY,
    account_id     BIGINT      NOT NULL REFERENCES account (id) ON DELETE CASCADE,
    card_id        VARCHAR(50) NOT NULL UNIQUE,
    payment_system VARCHAR(20) NOT NULL,
    status         VARCHAR(50) NOT NULL
);

CREATE TABLE payment
(
    id           BIGSERIAL PRIMARY KEY,
    account_id   BIGINT         NOT NULL REFERENCES account (id) ON DELETE CASCADE,
    payment_date DATE           NOT NULL,
    amount       NUMERIC(15, 2) NOT NULL,
    is_credit    BOOLEAN        NOT NULL,
    payed_at     TIMESTAMP,
    type         VARCHAR(50)    NOT NULL
);

CREATE TABLE transaction
(
    id         BIGSERIAL PRIMARY KEY,
    account_id BIGINT         NOT NULL REFERENCES account (id) ON DELETE CASCADE,
    card_id    BIGINT         REFERENCES card (id) ON DELETE SET NULL,
    type       VARCHAR(50)    NOT NULL,
    amount     NUMERIC(15, 2) NOT NULL,
    status     VARCHAR(20)    NOT NULL CHECK (status IN ('ALLOWED', 'PROCESSING', 'COMPLETE', 'BLOCKED', 'CANCELLED')),
    timestamp  TIMESTAMP      NOT NULL
);
