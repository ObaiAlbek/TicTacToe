package game;
public class Game {

    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public Game(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }

    public void startGame() {
        System.out.println("Willkommen zu Tic Tac Toe!");
        board.printBoard();

        while (!isGameOver()) {
            currentPlayer.makeMove(board);
            board.printBoard();

            if (board.isWinningMove(currentPlayer.getSymbol())) {
                System.out.println("Spieler " + currentPlayer.getSymbol() + " hat gewonnen!");
                return;
            }

            if (board.isBoardFull()) {
                System.out.println("Unentschieden! Kein Feld mehr frei.");
                board.printBoard();
                return;
            }

            switchPlayer();
        }
    }

    public boolean makeMove(int row, int col) {
        // Führt einen Zug des aktuellen Spielers aus
        return board.makeMove(currentPlayer.getSymbol(), row, col);
    }

    public void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public boolean isGameOver() {
        // Gewinn oder Unentschieden
        if (board.isWinningMove(player1.getSymbol()) ||
            board.isWinningMove(player2.getSymbol()) ||
            board.isBoardFull()) {
            return true;
        }
        return false;
    }
}
