# Tic Tac Toe – Objektorientiertes Java-Projekt

Dieses Projekt implementiert ein vollständiges **Tic-Tac-Toe-Spiel** in Java im Rahmen des Moduls *Programmierung 1*.  
Der Fokus liegt auf **Klassen**, **Vererbung**, **abstrakten Methoden**, **sauberer Architektur** und optional einem **Monte-Carlo-Algorithmus** für den Computergegner.

---

## 🎯 Features

- ✔️ Objektorientierte Struktur mit eigenen Klassen  
- ✔️ `Board`, `Game`, `Player`, `HumanPlayer`, `ComputerPlayer`  
- ✔️ Mensch gegen Computer  
- ✔️ Validierung von Eingaben  
- ✔️ Zufällige Züge oder Monte-Carlo-KI  
- ✔️ Sauber strukturiert, kommentiert und erweiterbar  

---

## 🧱 Klassenübersicht (UML)

Das folgende Klassendiagramm ist in **Mermaid-UML-Syntax** geschrieben und kann direkt von GitHub gerendert werden.

```mermaid
classDiagram
    class Board {
        -char[][] board
        +Board()
        +printBoard()
        +isValidMove(int row, int col) boolean
        +makeMove(char player, int row, int col) boolean
        +isWinningMove(char player) boolean
        +isBoardFull() boolean
        +copy() Board
        +getBoard() char[][]
    }

    class Player {
        #char symbol
        +Player(char symbol)
        +getSymbol() char
        <<abstract>> makeMove(Board board)
    }

    class HumanPlayer {
        +HumanPlayer(char symbol, Scanner scanner)
        +makeMove(Board board)
    }

    class ComputerPlayer {
        -Random random
        -static int SIMULATIONS
        +ComputerPlayer(char symbol)
        +makeMove(Board board)
        -getPossibleMoves(Board board) List~int[]~
    }

    class Game {
        -Board board
        -Player player1
        -Player player2
        -Player currentPlayer
        +Game(Player p1, Player p2)
        +startGame()
        +makeMove(int row, int col) boolean
        +switchPlayer()
        +isGameOver() boolean
    }

    Player <|-- HumanPlayer
    Player <|-- ComputerPlayer
    Game --> Board
    Game --> Player
````

---

## ▶️ Ausführen des Programms

### 1. Projekt kompilieren

```bash
javac *.java
```

### 2. Spiel starten

```bash
java TicTacToeMain
```

---

## 🧠 Monte-Carlo Algorithmus (optional)

Der Computergegner kann mithilfe eines **Monte-Carlo-Simulators** den besten Zug schätzen:

1. Für jeden möglichen Zug wird das Board kopiert.
2. Das Spiel wird aus dieser Stellung **1000-mal zufällig zu Ende gespielt**.
3. Die Gewinnwahrscheinlichkeit wird berechnet:
   [
   winRate = \frac{winCount}{winCount + lostCount}
   ]
4. Der Zug mit der höchsten `winRate` wird gewählt.

Diese Technik wird in KI-Systemen wie Go, Schach oder Backgammon eingesetzt.

---

## 📂 Projektstruktur

```
/src
 ├── Board.java
 ├── Game.java
 ├── Player.java
 ├── HumanPlayer.java
 ├── ComputerPlayer.java
 └── TicTacToeMain.java
```

---

## 📌 Lernziele des Projekts

* Klassen & Objekte
* Vererbung & Polymorphie
* Abstrakte Klassen
* saubere API-Designs
* Zufallszahlen & Simulationen
* einfache KI-Konzepte (Monte-Carlo)
