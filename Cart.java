import java.util.ArrayList;
import java.util.List;

// Represents a shopping cart in the e-commerce system
public class Cart {
    private List<CartItems> items;

    // Constructor
    public Cart() {
        items = new ArrayList<>();
    }

    // Adds an item to the cart
    public void addItem(Product product, int quantity) {
        items.add(new CartItems(product, quantity));
    }

    // Removes an item from the cart
    public void removeItem(Product product) {
        items.removeIf(item -> item.getProduct().equals(product));
    }

    // Calculates the total price of the items in the cart
    public double getTotalPrice() {
        return items.stream().mapToDouble(CartItems::getTotalPrice).sum();
    }

    // Provides a string representation of the cart
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CartItems item : items) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }
}
