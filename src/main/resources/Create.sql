CREATE TABLE IF NOT EXISTS readers (
    readerID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS books (
    bookID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(80) NOT NULL,
    pages INTEGER DEFAULT 0
);

CREATE TABLE IF NOT EXISTS rents (
    rentID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    readerID INTEGER NOT NULL,
    bookID INTEGER NOT NULL,
    startDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    endDate TIMESTAMP,

    FOREIGN KEY (readerID) REFERENCES readers(readerID) ON DELETE CASCADE,
    FOREIGN KEY (bookID) REFERENCES books(bookID) ON DELETE CASCADE
);