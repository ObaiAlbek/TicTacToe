package game;

public class Board {

    private char[][] board;

    public Board() {
        board = new char[3][3];
        // leeres Spielfeld initialisieren
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = ' ';
            }
        }
    }

    public void printBoard() {
        System.out.println("  0   1   2");
        for (int r = 0; r < 3; r++) {
            System.out.print(r + " ");
            for (int c = 0; c < 3; c++) {
                System.out.print(board[r][c]);
                if (c < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (r < 2) {
                System.out.println(" ---+---+---");
            }
        }
    }

    public boolean isValidMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            return false;
        }
        return board[row][col] == ' ';
    }

    public boolean makeMove(char player, int row, int col) {
        if (!isValidMove(row, col)) {
            return false;
        }
        board[row][col] = player;
        return true;
    }

    public boolean isWinningMove(char player) {
        // Zeilen
        for (int r = 0; r < 3; r++) {
            if (board[r][0] == player &&
                board[r][1] == player &&
                board[r][2] == player) {
                return true;
            }
        }

        // Spalten
        for (int c = 0; c < 3; c++) {
            if (board[0][c] == player &&
                board[1][c] == player &&
                board[2][c] == player) {
                return true;
            }
        }

        // Diagonalen
        if (board[0][0] == player &&
            board[1][1] == player &&
            board[2][2] == player) {
            return true;
        }

        if (board[0][2] == player &&
            board[1][1] == player &&
            board[2][0] == player) {
            return true;
        }

        return false;
    }

    public boolean isBoardFull() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Für Monte-Carlo / Kopien später nützlich:
    public Board copy() {
        Board copy = new Board();
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                copy.board[r][c] = this.board[r][c];
            }
        }
        return copy;
    }

    public char[][] getBoard() {
        return board;
    }
}
