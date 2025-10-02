# Tic-Tac-Toe (Text-based, Java)

A two-player Tic-Tac-Toe game in the terminal, with optional computer opponent and difficulty levels.

## UML Diagram
![UML Diagram](docs/treirad-uml.png)

### Legend
\+ public  
\- private  
→ Association  
-▷ Implementation (interface)

## Project Structure
The project is divided into packages for clarity and scalability:

- **ai** → AI strategies (Random, Heuristic, Minimax). Also contains Difficulty enum.
- **app** → Entry point and game loop (Main, Game).
- **model** → Core classes (Board, Scoreboard, Mark enum).
- **player** → Player interface + implementations (HumanPlayer, ComputerPlayer).

### Why this structure?
- **Separation of concerns** → Each package has its own responsibility.
- **Scalability** → Easier to add new features, like new AI strategies.
- **Readability** → Other developers can quickly understand the flow.

###  Directory tree
```
src/
├── ai/
│   ├── AiStrategy.java
│   ├── Difficulty.java
│   ├── HeuristicStrategy.java
│   ├── MinimaxStrategy.java
│   └── RandomStrategy.java
├── app/
│   ├── Game.java
│   └── Main.java
├── model/
│   ├── Board.java
│   ├── Mark.java
│   └── Scoreboard.java
└── player/
    ├── ComputerPlayer.java
    ├── HumanPlayer.java
    └── Player.java
```
## Tests
Unit tests are written with **JUnit 5**.  
The test sources live under `test/` with the same package structure as `src/`.

### Current coverage
- **BoardTest** → covers placing marks, win conditions (row, column, diagonals), full board, and invalid moves.
- **ScoreboardTest** → covers recording wins, tracking multiple players, accumulating wins per player, and handling unknown players.

## Build & Run
Standard Java project (no external deps). Code lives under `src/`.

## Roadmap (MVP → VG)
* ✅ Two human players (terminal)
* ✅ Win/draw detection, input validation
* ✅ Restart after game ends
* ✅ OOP structure (Board, Game, Player, Scoreboard)
* ✅ JUnit tests for Board and Scoreboard
* ⭐ Names & turn prompts
* ⭐ Scoreboard (wins per player)
* ⭐ Input error handling (robust)
* ⭐ Computer player (Random / Heuristic / Minimax)
* ⭐ Difficulty selection (EASY / MEDIUM / HARD)


