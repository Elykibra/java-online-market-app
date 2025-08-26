/**
 * Represents a generic item that can be sold in the market.
 * This is the base class for specific item types.
 */
public class MarketItem {
    private String name;
    private double price;

    public MarketItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}