-- Crear tabla de videojuegos
CREATE TABLE IF NOT EXISTS videogames (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    platform VARCHAR(50) NOT NULL,
    price DECIMAL(6,2) NOT NULL
);

-- Crear tabla de DLCs
CREATE TABLE IF NOT EXISTS dlc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    videogame_id INT NOT NULL,
    FOREIGN KEY (videogame_id) REFERENCES videogames(id) ON DELETE CASCADE
);


