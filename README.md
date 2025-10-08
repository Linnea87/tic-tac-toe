# Tic-Tac-Toe (Text-based, Java)

A two-player Tic-Tac-Toe game in the terminal, with optional computer opponent and difficulty levels.
This project was developed as part of a school assignment in Java programming.
## UML Diagram
![UML Diagram](docs/treirad-uml.png)

*Note: This UML represents the basic structure and planning of the project.  
The final implementation may differ slightly as the project evolved.*


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
- **util** → Console helpers and shared utilities:
    - `ConsoleUI` (headings, colored messages, helpers)
    - `ConsoleColors` (ANSI color codes)
    - `Messages` (centralized prompts/errors)
    - `NameValidator` (validates and formats player names)
    - `CellParser` (parses inputs like `A1` → cell index)

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
├── player/
│   ├── ComputerPlayer.java
│   ├── HumanPlayer.java
│   └── Player.java
└── util/
    ├── CellParser.java
    ├── ConsoleColors.java
    ├── ConsoleUI.java
    ├── Messages.java
    └── NameValidator.java
```
## Tests
Unit tests are written with **JUnit 5**.  
The test sources live under `test/` with the same package structure as `src/`.

### Directory tree (tests)
```
test/
├── ai/
│   ├── RandomStrategyTest.java
├── app/
│   ├── GameTest.java
├── model/
│   ├── BoardTest.java
│   ├── ScoreboardTest.java
├── player/
│   ├── ComputerPlayerTest.java
│   ├── HumanPlayerTest.java
└── util/
    ├── NameValidatorTest.java
```
### Run tests
- In IntelliJ: right-click on `test/` ➜ **Run 'All Tests'**
- Or via Maven/Gradle if you add a build tool later (e.g., `mvn test`)

### Current coverage
- **BoardTest** → covers placing marks, win conditions (row, column, diagonals), full board, and invalid moves.
- **ScoreboardTest** → covers recording wins, tracking multiple players, accumulating wins per player, and handling unknown players.
- **NameValidatorTest** → ensures valid names pass and invalid names throw exceptions.
- **HumanPlayerTest** → validates constructor args and tests the input loop for choosing cells.
- **ComputerPlayerTest** → ensures computer moves are chosen within valid bounds and only on empty cells.
- **RandomStrategyTest** → checks that AI strategy always returns a valid, empty cell.
- **GameTest** → runs full rounds end-to-end:
    - Player X winning a game
    - A draw scenario
    - Playing against the computer (**ComputerPlayer + RandomStrategy**)
    - Scoreboard and restart flow


## Build & Run
Standard Java project (no external deps). Code lives under `src/`.

### How to play (input)
Enter moves in the format **Column + Row**, e.g. `A1`, `B2`, `C3`.

#### Empty board
```
    A   B   C
1     |   |  
   ---+---+---
2     |   |  
   ---+---+---
3     |   |  
```
#### Example mid-game
```
    A   B   C
1   X | O |  
   ---+---+---
2     | X |  
   ---+---+---
3   O |   | X
```

## Roadmap
* ✅ Two human players (terminal)
* ✅ Win/draw detection, input validation
* ✅ Restart after game ends
* ✅ OOP structure (Board, Game, Player, Scoreboard)
* ✅ JUnit tests for Board and Scoreboard
* ✅ Names & turn prompts
* ✅ Input error handling (robust)
* ✅ Computer player (Random / Heuristic / Minimax)
* ✅ Difficulty selection (EASY / MEDIUM / HARD)


## Credits
* AI tools (e.g. ChatGPT) were used occasionally for debugging assistance, code reviews, and documentation improvements.  
* All final design decisions and implementations were made by me.  

