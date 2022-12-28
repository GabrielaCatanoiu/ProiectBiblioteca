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
    date_birth VARCHAR(255) NOT NULL,
    date_death VARCHAR(255),

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS city (
    id BIGINT NOT NULL AUTO_INCREMENT,
    city_name VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    district VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS publishing_house (
    id BIGINT NOT NULL AUTO_INCREMENT,
    publishing_name VARCHAR(255) NOT NULL UNIQUE,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS city_publishing_house (
    id BIGINT NOT NULL AUTO_INCREMENT,
    publishing_house BIGINT NOT NULL,
    city BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (publishing_house) REFERENCES publishing_house(id),
    FOREIGN KEY (city) REFERENCES city(id)
);

CREATE TABLE IF NOT EXISTS book (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL UNIQUE,
    year_published VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS author_book (
    id BIGINT NOT NULL AUTO_INCREMENT,
    book BIGINT NOT NULL,
    author BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (book) REFERENCES book(id),
    FOREIGN KEY (author) REFERENCES author(id)
);

CREATE TABLE IF NOT EXISTS literature_category (
    id BIGINT NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS city_publishing_house_book (
    id BIGINT NOT NULL AUTO_INCREMENT,
    book BIGINT NOT NULL,
    city_publishing_house BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (book) REFERENCES book(id),
    FOREIGN KEY (city_publishing_house) REFERENCES city_publishing_house(id)
);