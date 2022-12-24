CREATE TABLE IF NOT EXISTS member (
    id BIGINT NOT NULL AUTO_INCREMENT,
    m_name VARCHAR(255) NOT NULL UNIQUE,
    surname VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(255),

    PRIMARY KEY (id)
);

