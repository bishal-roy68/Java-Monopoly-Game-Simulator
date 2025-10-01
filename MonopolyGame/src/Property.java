// This code implement the property part of the game
// A property has a name, price, rent, and an owner

public class Property {
    String name;
    int purchasePrice;
    int rentPrice;
    Player owner;

    public Property(String name, int purchasePrice, int rentPrice) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.rentPrice = rentPrice;
        this.owner = null; // no owner at the start
    }
}
