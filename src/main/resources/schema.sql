CREATE TABLE IF NOT EXISTS member (
    id BIGINT NOT NULL AUTO_INCREMENT,
    m_name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(255) NOT NULL UNIQUE,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS borrowed_books (
    id BIGINT NOT NULL AUTO_INCREMENT,
    date_due VARCHAR(255) NOT NULL,
    date_returned VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS member_borrowed_books (
    id BIGINT NOT NULL AUTO_INCREMENT,
    member BIGINT NOT NULL,
    borrowed_books BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (member) REFERENCES member(id),
    FOREIGN KEY (borrowed_books) REFERENCES borrowed_books(id)
);

CREATE TABLE IF NOT EXISTS author (
    id BIGINT NOT NULL AUTO_INCREMENT,
    author_name VARCHAR(255) NOT NULL,
    author_surname VARCHAR(255) NOT NULL,
    date_birt VARCHAR(255) NOT NULL,
    date_death VARCHAR(255),

    PRIMARY KEY (id)
);