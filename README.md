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
├── config/                 Database connection settings
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

<img width="423" height="419" alt="Screenshot 2026-06-26 at 12 08 14" src="https://github.com/user-attachments/assets/7d7012cb-bb30-415f-a002-82e377bd2bde" />



Game Window

<img width="508" height="587" alt="Screenshot 2026-06-26 at 12 09 11" src="https://github.com/user-attachments/assets/e578e13c-eb38-4d48-a6a6-a00256e88863" />



Top 5 Scorers

<img width="691" height="455" alt="Screenshot 2026-06-26 at 12 08 51" src="https://github.com/user-attachments/assets/0cf940ce-425c-4eae-96ba-ccb5263705bd" />




YouTube link: https://youtu.be/y1pDPyFDwcg



Repository link: **https://github.com/moengilsan/danendra_116-swing-game-project**

