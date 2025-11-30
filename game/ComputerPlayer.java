package game;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {

    private Random random;
    private static final int SIMULATIONS = 1000; // N (z.B. 1000 Mal pro möglichem Zug)

    public ComputerPlayer(char symbol) {
        super(symbol);
        this.random = new Random();
    }

    @Override
    public void makeMove(Board board) {
        // alle möglichen Züge ermitteln
        List<int[]> possibleMoves = getPossibleMoves(board);

        if (possibleMoves.isEmpty()) {
            return; // kein Zug möglich
        }

        char opponentSymbol = (symbol == 'X') ? 'O' : 'X';

        double bestWinRate = -1.0;
        int[] bestMove = null;

        // Für jeden möglichen Zug Monte-Carlo-Simulation
        for (int[] move : possibleMoves) {
            int row = move[0];
            int col = move[1];

            int winCount = 0;
            int lostCount = 0;

            for (int i = 0; i < SIMULATIONS; i++) {
                // 1. Kopie des Boards
                Board simBoard = board.copy();

                // 2. Kandidatenzug des Computers ausführen
                simBoard.makeMove(symbol, row, col);

                // Nächster Spieler ist der Gegner
                char currentPlayer = opponentSymbol;

                // 3. Zufällige Partie bis zum Ende
                while (true) {
                    // Gewinn-/Verlustprüfung
                    if (simBoard.isWinningMove(symbol)) {
                        winCount++;
                        break;
                    }
                    if (simBoard.isWinningMove(opponentSymbol)) {
                        lostCount++;
                        break;
                    }
                    if (simBoard.isBoardFull()) {
                        // Unentschieden -> weder Sieg noch Niederlage
                        break;
                    }

                    // zufälligen gültigen Zug für currentPlayer wählen
                    List<int[]> simMoves = getPossibleMoves(simBoard);
                    if (simMoves.isEmpty()) {
                        // sollte durch isBoardFull abgedeckt sein, aber zur Sicherheit:
                        break;
                    }
                    int[] simMove = simMoves.get(random.nextInt(simMoves.size()));
                    simBoard.makeMove(currentPlayer, simMove[0], simMove[1]);

                    // Spieler wechseln
                    currentPlayer = (currentPlayer == symbol) ? opponentSymbol : symbol;
                }
            }

            // 4. Gewinnrate berechnen
            double winRate;
            if (winCount + lostCount == 0) {
                // nur Unentschieden -> neutral behandeln
                winRate = 0.5;
            } else {
                winRate = (double) winCount / (winCount + lostCount);
            }

            // 5. Besten Zug merken
            if (winRate > bestWinRate) {
                bestWinRate = winRate;
                bestMove = move;
            }
        }

        // 6. Besten Zug auf dem echten Board ausführen
        if (bestMove != null) {
            board.makeMove(symbol, bestMove[0], bestMove[1]);
            System.out.println(
                "Computer (" + symbol + ") setzt (Monte Carlo) auf (" +
                bestMove[0] + ", " + bestMove[1] + "), winRate ~ " + bestWinRate
            );
        }
    }

    /**
     * Hilfsmethode: liefert alle noch freien Felder als Liste von [row, col].
     */
    private List<int[]> getPossibleMoves(Board board) {
        List<int[]> moves = new ArrayList<>();
        char[][] b = board.getBoard();
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (b[r][c] == ' ') {
                    moves.add(new int[]{r, c});
                }
            }
        }
        return moves;
    }
}
