import java.util.Scanner;

/**
 * Main application class for the Online Market simulation.
 * Handles user authentication and the main navigation menu.
 */
public class OnlineMarket {

    // Make dependencies static so they can be used in static methods
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserDatabase userDatabase = new UserDatabase();
    private static final Market market = new Market();
    private static final VoucherDatabase voucherDatabase = new VoucherDatabase();

    public static void main(String[] args) {
        System.out.println("Welcome to STI Shopping!");

        // Authenticate the user first by showing the menu
        if (!authenticateUser()) {
            System.out.println("Exiting application. Goodbye!");
            return; // Exit if login/registration fails or user chooses to exit
        }
        
        // If authentication is successful, run the shopping part
        ShoppingCart cart = new ShoppingCart(voucherDatabase);
        runShoppingCart(cart);
        
        scanner.close();
        System.out.println("Thank you for shopping with us!");
    }

    /**
     * Handles the user login and registration process with a menu.
     * @return true if the user is successfully authenticated, false otherwise.
     */
    private static boolean authenticateUser() {
        while (true) {
            System.out.println("\n--- Authentication ---");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        if (userDatabase.authenticate(username, password)) {
                            System.out.println("\nLogin successful! Welcome, " + username + ".");
                            return true;
                        } else {
                            System.out.println("Invalid username or password.");
                        }
                        break;
                    case 2:
                        System.out.print("Enter new username: ");
                        String newUsername = scanner.nextLine();
                        System.out.print("Enter new password: ");
                        String newPassword = scanner.nextLine();
                        userDatabase.addUser(newUsername, newPassword);
                        System.out.println("\nRegistration successful! You are now logged in.");
                        return true;
                    case 3:
                        return false; // User chose to exit
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    /**
     * Runs the main shopping loop after a user is logged in.
     * @param cart The user's shopping cart.
     */
    private static void runShoppingCart(ShoppingCart cart) {
        boolean shopping = true;
        while (shopping) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Shop by Category");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                int mainChoice = Integer.parseInt(scanner.nextLine());
                switch (mainChoice) {
                    case 1:
                        market.shopByCategory(cart, scanner);
                        break;
                    case 2:
                        cart.displayCart();
                        break;
                    case 3:
                        if (cart.isEmpty()) {
                            System.out.println("Your cart is empty. Please add items before checking out.");
                        } else {
                            cart.displayCart();
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
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}