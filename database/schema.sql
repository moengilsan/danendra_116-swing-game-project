IF DB_ID('game_project') IS NULL
BEGIN
    CREATE DATABASE game_project;
END;
GO

USE game_project;
GO

IF OBJECT_ID('dbo.players', 'U') IS NULL
BEGIN
    CREATE TABLE dbo.players (
        id INT IDENTITY(1,1) PRIMARY KEY,
        username VARCHAR(50) NOT NULL UNIQUE,
        password VARCHAR(100) NOT NULL,
        wins INT NOT NULL DEFAULT 0,
        losses INT NOT NULL DEFAULT 0,
        draws INT NOT NULL DEFAULT 0,
        score INT NOT NULL DEFAULT 0
    );
END;
GO

IF NOT EXISTS (SELECT 1 FROM dbo.players WHERE username = 'student1')
    INSERT INTO dbo.players (username, password) VALUES ('student1', '12345');
IF NOT EXISTS (SELECT 1 FROM dbo.players WHERE username = 'student2')
    INSERT INTO dbo.players (username, password) VALUES ('student2', '12345');
IF NOT EXISTS (SELECT 1 FROM dbo.players WHERE username = 'student3')
    INSERT INTO dbo.players (username, password) VALUES ('student3', '12345');
IF NOT EXISTS (SELECT 1 FROM dbo.players WHERE username = 'student4')
    INSERT INTO dbo.players (username, password) VALUES ('student4', '12345');
IF NOT EXISTS (SELECT 1 FROM dbo.players WHERE username = 'student5')
    INSERT INTO dbo.players (username, password) VALUES ('student5', '12345');
GO

SELECT id, username, wins, losses, draws, score
FROM dbo.players
ORDER BY id;
GO
