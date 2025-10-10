# 🎮 Tic-Tac-Toe

![Tic-Tac-Toe Mockup](docs/tictactoe-mockup.png)

## Table of Contents

- [Introduction](#introduction)
    - [Project goals](#project-goals)
- [Data Model](#data-model)
    - [UML Diagram](#uml-diagram)
    - [Project Structure](#project-structure)
    - [Design](#design)
- [Features](#features)
    - [Existing features](#existing-features)
    - [AI Behavior & Strategy](#ai-behavior--strategy)
    - [Future features](#future-features)
- [Technologies](#technologies)
- [Testing](#testing)
    - [Input Validation](#input-validation)
    - [Code Validation](#code-validation)
    - [Tests result](#tests-result)
- [Bugs](#bugs)
    - [Solved Bugs](#solved-bugs)
    - [Unresolved Bugs](#unresolved-bugs)
- [Deployment](#deployment)
    - [Adding, committing and pushing code](#adding-committing-and-pushing-code)
    - [Cloning and Forking](#cloning-and-forking)
    - [Running the project locally](#running-the-project-locally)
    - [Gameplay Instructions](#gameplay-instructions)
- [Credits](#-credits)

## 📖 Introduction

This is a text-based version of the classic Tic-Tac-Toe game. It is a fully interactive, terminal-based project designed with a focus on user experience, clean code architecture, and scalability. Players can compete in human vs. human or human vs. AI modes, with three different AI difficulty levels.

### Project goals

- Write clean, maintainable, and well-structured Java code.
- Design a user-friendly, readable, and engaging terminal game.
- Explore object-oriented programming principles in a practical context.
- Implement robust input validation and helpful user feedback.
- Create a visually engaging and intuitive experience — even in a text-based environment.

[⬆ Back to top](#table-of-contents)

## 🧩 Data Model

### 🗺️ UML Diagram

The UML diagram below was created in Lucidchart to outline the initial structure and relationships between the main components of the game. It illustrates how the core parts 
— including game logic, AI strategies, and player classes — were originally designed to interact.

While the diagram provided a solid foundation during early development, the final implementation evolved as new features and supporting classes were introduced.

![UML Diagram](docs/treirad-uml.png)

**Legend:**  
\+ public  
\- private  
→ Association  
-▷ Implementation (interface)

### 🗂️ Project Structure

The project is organized into packages based on functionality, improving readability, maintainability, and scalability:
- app → Handles game flow and user interaction.
- model → Defines core data and rules.
- player → Abstracts human and AI behavior.
- ai → Provides interchangeable AI strategy implementations.
- util → Centralizes helpers and shared logic.

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
    ├── Grid.java
    ├── Messages.java
    └── NameValidator.java
```

### 🎨 Design

A key design goal was to make the game as engaging and user-friendly as possible, even within a terminal environment.
Some key design decisions:
- Color usage – ANSI colors highlight errors, prompts, input, and narration.
- Consistency – Each player keeps the same color to clearly show turns.
- Clear formatting – Output uses headings, spacing, and labels for readability.
- Helpful feedback – Input errors are highlighted immediately with instructions.

[⬆ Back to top](#table-of-contents)

## ✨ Features

### 🔧 Existing features

- Human vs Human and Human vs AI gameplay modes.
- Adjustable board size (3×3 up to 10×10) in Human vs Human mode.
- Three AI difficulty levels: Random, Heuristic, and Minimax.
- Robust input validation with clear error messages.
- Comprehensive scoreboard tracking wins and draws.
- Centralized message management in Messages.java for easy localization.
- ANSI-colored console output for enhanced readability and engagement.

#### 🤖 AI Behavior & Strategy

| Difficulty | Strategy Class | Description |
|------------|------------------|--------------|
| **Easy** | `RandomStrategy` | Selects a random empty cell — simple and unpredictable. |
| **Medium** | `HeuristicStrategy` | Blocks or extends potential winning lines — more defensive. |
| **Hard** | `MinimaxStrategy` | Evaluates all possible future states to choose the optimal move. |

### 🌟 Future features

- Persistent player statistics across sessions.
- Monthly challenges or mini-tournaments.
- Enhanced AI logic for larger board sizes.
- Support for multiple game variants (e.g., “4-in-a-row”).

[⬆ Back to top](#table-of-contents)

## 🛠️ Technologies

- Java 21 – Core language and standard libraries.
- JUnit 5 – Unit testing framework.
- Git – Version control.
- GitHub – Repository hosting and collaboration.
- IntelliJ IDEA – Development environment.

[⬆ Back to top](#table-of-contents)

## 🧪 Testing

Testing was performed continuously throughout the project using JUnit 5, with both manual and automated methods. This ensured correct behavior, robust edge-case handling, and system stability during refactoring.

### 🛡️ Input Validation

All critical inputs (player names, board size, move coordinates) are validated before processing. Invalid input triggers clear, colored error messages and prompts for retry.

### 🧰 Code Validation

- All classes and methods follow SRP (Single Responsibility Principle).- All classes and methods follow SRP (Single Responsibility Principle).
- The project passes all JUnit tests without errors.
- Code follows OOP and clean-code best practices.

### 📊 Tests result:
[View all test files here](https://github.com/Linnea87/tic-tac-toe/tree/main/test)

The table below provides a detailed overview of the unit tests implemented for this project. Each test verifies key functionality to ensure that the system behaves as expected, remains stable under different scenarios, and meets quality and reliability standards.

#### `model` Package Tests

| Test Class | Description | Status |
|------------|------------|--------|
| **BoardTest** | Validates placing marks, win conditions (rows, columns, diagonals), full board detection, and invalid moves. | ✅ |
| **ScoreboardTest** | Ensures correct tracking of wins per player and handling of multiple players. | ✅ |

#### `util` Package Tests

| Test Class | Description | Status |
|------------|------------|--------|
| **NameValidatorTest** | Checks that valid and invalid names are handled correctly. | ✅ |
| **CellParserTest** | Validates and parses user input like `A1` into board positions and throws correct exceptions for invalid input. | ✅ |
| **MessagesTest** | Ensures all user-facing messages are displayed correctly and consistently. | ✅ |
| **ConsoleUITest** | Verifies formatted console output, colors, and layout helpers. | ✅ |
| **ConsoleColorsTest** | Ensures ANSI color codes are applied and rendered correctly. | ✅ |

#### `player` Package Tests

| Test Class | Description | Status |
|------------|------------|--------|
| **HumanPlayerTest** | Tests player input handling and constructor validation. | ✅ |
| **ComputerPlayerTest** | Ensures AI moves are only made in valid, empty cells. | ✅ |

#### `ai` Package Tests

| Test Class | Description | Status |
|------------|------------|--------|
| **RandomStrategyTest** | Verifies AI always selects valid empty cells. | ✅ |
| **HeuristicStrategyTest** | Ensures AI correctly blocks or extends winning lines. | ✅ |
| **MinimaxStrategyTest** | Tests minimax decision-making logic and ensures optimal moves. | ✅ |

#### `app` Package Tests

| Test Class | Description | Status |
|------------|------------|--------|
| **MenuTest** | Validates menu navigation, option selection, and user flow. | ✅ |
| **GameTest** | Full end-to-end simulation: verifies player X winning a game (including board size selection), draw scenarios, AI matches at all difficulty levels, and scoreboard + restart flow. | ✅ |

[⬆ Back to top](#table-of-contents)

## 🐛 Bugs

### 🔨 Solved Bugs

- Fixed crash caused by invalid user input.
- Corrected cell parsing to handle out-of-range inputs.

### 🐞 Unresolved Bugs

- None known.

[⬆ Back to top](#table-of-contents)

## 🚀 Deployment

### 📤 Adding, committing and pushing code

```
git add <file>
git commit -m "commit message"
git push
```

### 🍴 Cloning and Forking:

If you want to clone or fork this project, you can do so from my [GitHub](https://github.com/Linnea87/tic-tac-toe) repository.

#### Requirements:
- Java JDK 21 or later
- Git
- IntelliJ IDEA (or another Java IDE)

```
git clone https://github.com/Linnea87/tic-tac-toe.git
cd tic-tac-toe
```

### 💻 Running the project locally

#### In IntelliJ;

```
Right-click on Main.java → Run 'Main'
```

#### In terminal;
```
javac -d out src/**/*.java
java -cp out app.Main
```

### 🕹️ Gameplay Instructions

Moves are entered as Column + Row, e.g. A1, B2, C3.

#### Example empty board
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

[⬆ Back to top](#table-of-contents)

## 🙌 Credits
- Occasional debugging, code reviews, and documentation support by AI tools.
- [Carbon](https://carbon.now.sh) was used to create the terminal mockup image included in this README.
- [Lucidchart](https://www.lucidchart.com) was used to create the UML diagram.
  
[⬆ Back to top](#table-of-contents)

---

✨ _Best wishes and happy coding!_

**Linnéa Ternevik**


