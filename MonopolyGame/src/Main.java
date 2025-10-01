// This code start the game
// User can start new game or load from saved file
// Options: play round, save game, or quit

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board board = null;

        System.out.println("Do you want to (1) Start new game or (2) Load game?");
        String choice = sc.nextLine();

        if (choice.equals("2")) {
            board = GameSave.loadGame("savegame.txt");
        }
        if (board == null) {
            board = new Board();
            Player p1 = new Player("Messi", 1200);
            Player p2 = new Player("Ronaldo", 1200);
            board.addPlayer(p1);
            board.addPlayer(p2);
        }

        while (true) {
            System.out.println("\nOptions: (1) Play round, (2) Save game, (3) Quit");
            String option = sc.nextLine();

            if (option.equals("1")) {
                board.playRound();
            } else if (option.equals("2")) {
                GameSave.saveGame(board, "savegame.txt");
            } else if (option.equals("3")) {
                System.out.println("Exiting game...");
                break;
            }
        }
    }
}
