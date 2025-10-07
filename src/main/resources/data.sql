CREATE TABLE videogames (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    platform VARCHAR(50) NOT NULL,
    price DECIMAL(6,2) NOT NULL
);

INSERT INTO videogames (title, genre, platform, price) VALUES
('The Legend of Zelda: Tears of the Kingdom', 'Adventure', 'Switch', 69.99),
('Elden Ring', 'RPG', 'PC', 59.99),
('God of War Ragnarök', 'Action', 'PlayStation 5', 79.99),
('Baldurs Gate 3', 'RPG', 'PC', 39.99);

CREATE TABLE dlc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    videogame_id INT NOT NULL,
    FOREIGN KEY (videogame_id) REFERENCES videogame(id) ON DELETE CASCADE
);

INSERT INTO dlc (name, price, videogame_id) VALUES
('moto hyliana', 2,99, 1),
('Shadow of the edtree', 29.99, 2);
