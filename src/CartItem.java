/**
 * Represents an item within the shopping cart, pairing a MarketItem with a quantity.
 */
public class CartItem {
    private MarketItem item;
    private int quantity;

    public CartItem(MarketItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public MarketItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}