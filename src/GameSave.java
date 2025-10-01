// This code save and load the game using a text file
// Source for save/load idea: https://stackoverflow.com/questions/19784628/saving-game-state

import java.io.*;
import java.util.*;

public class GameSave {

    public static void saveGame(Board board, String filename) {
        try {
            FileWriter writer = new FileWriter(filename);

            for (Player p : board.players) {
                writer.write("PLAYER," + p.name + "," + p.money + "," + p.position + "\n");
            }

            for (Property prop : board.properties) {
                String ownerName = (prop.owner != null) ? prop.owner.name : "none";
                writer.write("PROPERTY," + prop.name + "," + prop.purchasePrice + "," + prop.rentPrice + "," + ownerName + "\n");
            }

            writer.close();
            System.out.println("Game saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error while saving game.");
        }
    }

    public static Board loadGame(String filename) {
        Board board = new Board();
        board.players.clear();
        board.properties.clear();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            HashMap<String, Player> playerMap = new HashMap<>();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("PLAYER")) {
                    String name = parts[1];
                    int money = Integer.parseInt(parts[2]);
                    int position = Integer.parseInt(parts[3]);
                    Player p = new Player(name, money);
                    p.position = position;
                    board.players.add(p);
                    playerMap.put(name, p);
                } else if (parts[0].equals("PROPERTY")) {
                    String name = parts[1];
                    int price = Integer.parseInt(parts[2]);
                    int rent = Integer.parseInt(parts[3]);
                    String ownerName = parts[4];
                    Property prop = new Property(name, price, rent);
                    if (!ownerName.equals("none")) {
                        prop.owner = playerMap.get(ownerName);
                    }
                    board.properties.add(prop);
                }
            }

            reader.close();
            System.out.println("Game loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Error while loading game.");
        }
        return board;
    }
}