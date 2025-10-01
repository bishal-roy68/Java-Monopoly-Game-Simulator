// This code implement the board of the game
// The board has properties and players, and it also controls the turns

import java.util.*;

public class Board {
    ArrayList<Property> properties;
    ArrayList<Player> players;
    Random random;

    public Board() {
        properties = new ArrayList<>();
        players = new ArrayList<>();
        random = new Random();

        // add some properties
        properties.add(new Property("Mediterranean Avenue", 60, 2));
        properties.add(new Property("Baltic Avenue", 60, 4));
        properties.add(new Property("Oriental Avenue", 100, 6));
        properties.add(new Property("Vermont Avenue", 100, 6));
        properties.add(new Property("Connecticut Avenue", 120, 8));
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public void takeTurn(Player player) {
        if (player.inJail) {
            System.out.println(player.name + " is in jail and skip this turn.");
            player.money -= 50;
            player.inJail = false;
            return;
        }

        int dice1 = random.nextInt(6) + 1; // Source: Oracle Docs - Random
        int dice2 = random.nextInt(6) + 1;
        int steps = dice1 + dice2;

        System.out.println(player.name + " rolled " + dice1 + " and " + dice2 + " = " + steps);

        player.position = (player.position + steps) % properties.size();

        if (player.position == 0) {
            player.money += 200;
            System.out.println(player.name + " passed GO and got $200!");
        }

        Property landed = properties.get(player.position);
        System.out.println(player.name + " landed on " + landed.name);

        if (landed.owner == null) {
            if (player.money >= landed.purchasePrice) {
                player.money -= landed.purchasePrice;
                landed.owner = player;
                System.out.println(player.name + " bought " + landed.name);
            }
        } else if (landed.owner != player) {
            player.money -= landed.rentPrice;
            landed.owner.money += landed.rentPrice;
            System.out.println(player.name + " paid rent $" + landed.rentPrice + " to " + landed.owner.name);
        }

        if (dice1 == dice2) {
            System.out.println(player.name + " rolled doubles and get another turn!");
            takeTurn(player); // recursion
        }

        if (player.money <= 0) {
            System.out.println(player.name + " is bankrupt!");
            for (Property prop : properties) {
                if (prop.owner == player) {
                    prop.owner = null;
                }
            }
        }
    }

    public void playRound() {
        for (Player p : players) {
            if (p.money > 0) {
                takeTurn(p);
            }
        }
    }
}
