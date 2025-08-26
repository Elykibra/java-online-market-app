import java.util.Scanner;

/**
 * Main application class for the Online Market simulation.
 * Handles user authentication and the main menu.
 */
public class OnlineMarket {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDatabase userDatabase = new UserDatabase();
        VoucherDatabase voucherDatabase = new VoucherDatabase();
        Market market = new Market();
        ShoppingCart cart = new ShoppingCart(voucherDatabase);
        
        // --- Authentication ---
        System.out.println("Welcome to STI Shopping!");
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        scanner.nextLine(); // Consume newline

        if (!userDatabase.authenticate(username, password)) {
            System.out.println("Invalid username or password. Exiting.");
            scanner.close();
            return;
        }
        
        System.out.println("\nLogin successful! Welcome, " + username + ".");

        // --- Main Shopping Loop ---
        boolean shopping = true;
        while (shopping) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Shop by Category");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    market.shopByCategory(cart, scanner);
                    break;
                case 2:
                    cart.displayCart();
                    break;
                case 3:
                    if (cart.isEmpty()) {
                        System.out.println("Cart is empty. Please add items first.");
                    } else {
                        cart.placeOrder(scanner);
                        shopping = false; // End session after checkout
                    }
                    break;
                case 0:
                    shopping = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        
        System.out.println("\nThank you for shopping with us!");
        scanner.close();
    }
}