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

CREATE TABLE IF NOT EXISTS borrowedBooks (
    id BIGINT NOT NULL AUTO_INCREMENT,
    date_due DATETIME,
    date_returned DATETIME,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS member_borrowedBooks (
    id BIGINT NOT NULL AUTO_INCREMENT,
    member BIGINT NOT NULL,
    borrowedBooks BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (member) REFERENCES member(id),
    FOREIGN KEY (borrowedBooks) REFERENCES borrowedBooks(id)
);

