// This code implement the player part of the game
// A player has a name, money, position, and can move on the board

public class Player {
    String name;
    int money;
    int position;
    boolean inJail;

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
        this.position = 0;   // start at GO
        this.inJail = false;
    }
}


