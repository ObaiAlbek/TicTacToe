package game;
public abstract class Player {

    protected char symbol; // 'X' oder 'O'

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    // muss in Unterklassen implementiert werden
    public abstract void makeMove(Board board);
}
