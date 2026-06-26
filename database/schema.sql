CREATE DATABASE IF NOT EXISTS game_project;

USE game_project;

CREATE TABLE IF NOT EXISTS players (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    wins INT NOT NULL DEFAULT 0,
    losses INT NOT NULL DEFAULT 0,
    draws INT NOT NULL DEFAULT 0,
    score INT NOT NULL DEFAULT 0
);

INSERT INTO players (username, password)
VALUES
    ('student1', '12345'),
    ('student2', '12345'),
    ('student3', '12345'),
    ('student4', '12345'),
    ('student5', '12345')
ON DUPLICATE KEY UPDATE username = VALUES(username);

SELECT id, username, wins, losses, draws, score
FROM players
ORDER BY id;
