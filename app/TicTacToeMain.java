package app;

import java.util.Scanner;
import game.ComputerPlayer;
import game.Game;
import game.HumanPlayer;
import game.Player;

public class TicTacToeMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Player human = new HumanPlayer('X', scanner);
        Player computer = new ComputerPlayer('O');

        Game game = new Game(human, computer);
        game.startGame();

        scanner.close();
    }
}
