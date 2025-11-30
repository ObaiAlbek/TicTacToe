package game;
import java.util.Scanner;

public class HumanPlayer extends Player {

    private Scanner scanner;

    public HumanPlayer(char symbol, Scanner scanner) {
        super(symbol);
        this.scanner = scanner;
    }

    @Override
    public void makeMove(Board board) {
        boolean valid = false;
        while (!valid) {
            System.out.println("Spieler " + symbol + " ist am Zug.");
            System.out.print("Bitte Zeile (0-2) eingeben: ");
            int row = scanner.nextInt();
            System.out.print("Bitte Spalte (0-2) eingeben: ");
            int col = scanner.nextInt();

            if (!board.isValidMove(row, col)) {
                System.out.println("Ungültiger Zug, bitte erneut versuchen.");
            } else {
                board.makeMove(symbol, row, col);
                valid = true;
            }
        }
    }
}
