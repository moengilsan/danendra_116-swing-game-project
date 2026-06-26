FunPro Arena - Java Swing Tic-Tac-Toe

Student Information

- Name: Danendra Yurafadi
- Student ID: 5026251116
- Class: Q
- Course: ES234211 - Programming Fundamental

## Project Description

FunPro Arena is a simple Tic-Tac-Toe game built with Java Swing and Microsoft SQL Server. Players log in using database credentials, play against a computer, save their results, view personal statistics, and view the Top 5 scorers.

## Features

- Swing login window with successful and failed login handling
- Main menu navigation
- 3 x 3 Tic-Tac-Toe board using Swing buttons
- Invalid move prevention
- Win, loss, and draw detection
- Random computer moves
- Persistent wins, losses, draws, and scores
- Personal statistics window
- Database-powered Top 5 leaderboard using `JTable`
- One database table only, as required by the assignment

## Scoring

| Result | Database update | Score |
|---|---|---:|
| Win | Wins + 1 | +10 |
| Draw | Draws + 1 | +3 |
| Loss | Losses + 1 | +0 |


## Project Structure

```text
.
├── src/                    Java application classes
├── database/schema.sql     One-table database schema and sample users
├── lib/                    SQL Server JDBC driver
└── screenshots/            Required GUI screenshots

```


Sample Login

```text
Username: student1
Password: 12345
```

The database also contains `student2` through `student5`, each with password
`12345`.


JDBC Driver

Download Microsoft SQL Server JDBC driver and place the `.jar` file in `lib/`. The launcher
looks for a filename like:

```text
mssql-jdbc-13.4.0.jre11.jar
```

Class Explanation

- `Main`: starts the Swing application on the event-dispatch thread.
- `DatabaseManager`: reads database settings and creates JDBC connections.
- `Player`: stores one player's ID, username, and statistics.
- `PlayerService`: performs login, statistic updates, player refresh, and Top 5 queries.
- `GameLogic`: stores the board and checks moves, winners, draws, and computer moves.
- `LoginFrame`: accepts username and password and opens the main menu.
- `MainMenuFrame`: provides navigation to the game and statistics windows.
- `GameFrame`: connects the nine board buttons to `GameLogic` and saves game results.
- `StatisticsFrame`: reloads and displays the logged-in player's statistics.
- `TopScorersFrame`: retrieves and displays the Top 5 players in a `JTable`.
- `UiStyle`: keeps colors, fonts, cards, and buttons consistent.

Screenshots

### Login Window

<img width="480" height="560" alt="login-window" src="https://github.com/user-attachments/assets/ba63109a-e625-443e-9623-27e4b5275d7b" />


Game Window

<img width="590" height="680" alt="game-window" src="https://github.com/user-attachments/assets/c12b1f51-08c6-4221-9ec4-06f3fdb2efe1" />


Top 5 Scorers

<img width="700" height="460" alt="top-scorers-window" src="https://github.com/user-attachments/assets/be1ebdfd-f74c-4baa-b2fc-a7ae71ff78e4" />


Testing Performed

- Compilation using Java 25
- Valid and invalid move checks
- Row, column, and diagonal winner checks
- Draw detection
- Computer move generation
- Correct and incorrect database login
- Statistics update and database persistence
- Top 5 retrieval with a maximum of five players



YouTube link: **ADD YOUR YOUTUBE LINK**



Repository link: **https://github.com/moengilsan/danendra_116-swing-game-project**

