import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the main market, containing different categories of items.
 * This class handles the logic for displaying categories and items to the user.
 */
public class Market {

    // Lists to hold items for each category
    private final List<MarketItem> appliances;
    private final List<MarketItem> vegetables;
    private final List<MarketItem> meats;
    private final List<MarketItem> fruits;

    /**
     * The constructor initializes the market with pre-defined items in each category.
     */
    public Market() {
        // Initialize Appliances
        appliances = Arrays.asList(
            new Appliance("50-inch Smart TV", 47000.00),
            new Appliance("Samsung Smart Refrigerator", 25000.00),
            new Appliance("Electric Kettle", 1500.00)
        );

        // Initialize Vegetables
        vegetables = Arrays.asList(
            new Vegetable("Tomatoes (1kg)", 100.00),
            new Vegetable("Potatoes (1kg)", 200.00),
            new Vegetable("Onions (1kg)", 120.00)
        );

        // Initialize Meats
        meats = Arrays.asList(
            new Meat("Chicken Breast (1kg)", 400.00),
            new Meat("Beef Sirloin (1kg)", 750.00)
        );

        // Initialize Fruits
        fruits = Arrays.asList(
            new Fruit("Philippine Mango (1kg)", 250.00),
            new Fruit("Strawberries (500g)", 800.00)
        );
    }

    /**
     * Displays the main category selection menu and handles user input.
     * @param cart The user's shopping cart to add items to.
     * @param scanner The Scanner object for reading user input.
     */
    public void shopByCategory(ShoppingCart cart, Scanner scanner) {
        boolean inCategoryMenu = true;
        while (inCategoryMenu) {
            System.out.println("\n--- Shop by Category ---");
            System.out.println("1. Appliances");
            System.out.println("2. Vegetables");
            System.out.println("3. Meats");
            System.out.println("4. Fruits");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");

            try {
                int categoryChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the rest of the line

                List<MarketItem> selectedCategory = null;
                switch (categoryChoice) {
                    case 1: selectedCategory = appliances; break;
                    case 2: selectedCategory = vegetables; break;
                    case 3: selectedCategory = meats; break;
                    case 4: selectedCategory = fruits; break;
                    case 0: inCategoryMenu = false; continue; // Exit this loop
                    default: System.out.println("Invalid choice. Please try again."); continue;
                }
                
                // If a valid category was chosen, show its items
                displayAndSelectItems(selectedCategory, cart, scanner);

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    /**
     * Displays items from a selected category and allows the user to add them to the cart.
     * @param items The list of items in the chosen category.
     * @param cart The user's shopping cart.
     * @param scanner The Scanner object for reading user input.
     */
    private void displayAndSelectItems(List<MarketItem> items, ShoppingCart cart, Scanner scanner) {
        boolean inItemMenu = true;
        while (inItemMenu) {
            System.out.println("\n--- Available Items ---");
            for (int i = 0; i < items.size(); i++) {
                MarketItem item = items.get(i);
                System.out.printf("%d. %s - Php %.2f%n", (i + 1), item.getName(), item.getPrice());
            }
            System.out.println("0. Back to Categories");
            System.out.print("Enter item number to add to cart: ");

            try {
                int itemChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (itemChoice == 0) {
                    inItemMenu = false; // Go back to category menu
                } else if (itemChoice > 0 && itemChoice <= items.size()) {
                    MarketItem selectedItem = items.get(itemChoice - 1);
                    System.out.print("Enter quantity for '" + selectedItem.getName() + "': ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    cart.addItem(selectedItem, quantity);
                } else {
                    System.out.println("Invalid item number.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}