// Represents an item in the shopping cart
public class CartItems {
    private Product product;
    private int quantity;

    // Constructor
    public CartItems(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Getter methods
    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    // Calculates the total price for the item
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    // Provides a string representation of the cart item
    @Override
    public String toString() {
        return "Product: " + product + " Quantity: " + quantity + " Total Price: " + getTotalPrice();
    }
}
