/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.onlinemarket;

/**
 *
 * @author kylea
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
 
class MarketItem {
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
 
class Appliance extends MarketItem {
    public Appliance(String name, double price) {
        super(name, price);
    }
}
 
class Vegetable extends MarketItem {
    public Vegetable(String name, double price) {
        super(name, price);
    }
}
 
class Meat extends MarketItem {
    public Meat(String name, double price) {
        super(name, price);
    }
}

class Fruit extends MarketItem {
    public Fruit(String name, double price) {
        super(name, price);
    }
}
 
class Market {
    private List<MarketItem> items;
    public Market(List<MarketItem> items) {
        this.items = items;
    }
 
    public void displayItems() {
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName() +  "- Php" + items.get(i).getPrice());
        }
    }
 
    public MarketItem getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        } else {
            return null;
        }
    }
}
 
class ShoppingCart {
    private List<CartItem> items;
    private VoucherDatabase voucherDatabase;
    
    private double calculateTotal() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getItem().getPrice() * item.getQuantity();
        }
        return total;
    }
    public ShoppingCart(VoucherDatabase voucherDatabase) {
        items = new ArrayList<>();
        this.voucherDatabase = voucherDatabase;
    }
 
    public void addItem(MarketItem item, int quantity) {
        items.add(new CartItem(item, quantity));
    }
 
    public void displayCart() {
        double total = 0;
        System.out.println("Cart:");
        for (CartItem item : items) {
            System.out.println(item.getItem().getName() + " x " + item.getQuantity() + " = $" + item.getItem().getPrice() + item.getQuantity());
            total += item.getItem().getPrice() + item.getQuantity();
        }
        System.out.println("Total Php" + total);
    }
 
    public void placeOrder(Scanner scanner) {
    System.out.print("Do you have a voucher code? (y/n): ");
    String hasVoucher = scanner.nextLine();
    
    if (hasVoucher.equalsIgnoreCase("y")) {
    System.out.print("Enter voucher code: ");
    String voucherCode = scanner.nextLine();
    System.out.println(" ");
    
    // Check if voucher is valid
    Voucher appliedVoucher = validateVoucher(voucherCode);
    if (appliedVoucher != null) {
            double total = calculateTotal(); // Calculate total before applying discount
            double discountAmount = total * appliedVoucher.getDiscount();
            total -= discountAmount; // Apply discount to total
            System.out.println("Voucher applied: " + appliedVoucher.getCode());
            System.out.println("Total after discount: Php" + total);
            String receipt = generateReceipt(total, appliedVoucher);
            System.out.println(receipt);
    } else {
        
      System.out.println("Invalid voucher code.");
        double totalinvalid = calculateTotal();
      String invalid = generateReceipt(totalinvalid, appliedVoucher);
      System.out.println(invalid);
    }
    } else {
      // Calculate total without discount
      double total = calculateTotal();
      System.out.println("Total Php" + total);
      
      // Generate receipt text notes without discount
      String receipt = generateReceipt(total, null);
      System.out.println(receipt);
    }
  
    // Implement checkout logic here, e.g., display total, payment processing, etc.
    System.out.println("Checkout complete!");
    simulateOrderProcessing();
    items.clear(); // Clear the cart after checkout
    }
    private String generateReceipt(double total, Voucher appliedVoucher) {
    StringBuilder receiptBuilder = new StringBuilder();
    receiptBuilder.append("**STI Shopping Receipt**\n");
    receiptBuilder.append("------------------------\n");
    Date currentDate = new Date();
    // printing base on time created.
    SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy_HHmmss");
    String formattedDate = formatter.format(currentDate);
    String filename = "receipt_" + formattedDate + ".txt";
    
    receiptBuilder.append("Date and Time: ").append(formattedDate).append("\n");

    // Loop through cart items and add details
    for (CartItem item : items) {
    receiptBuilder.append(item.getItem().getName()).append(" x ").append(item.getQuantity()).append(" = Php").append(item.getItem().getPrice() * item.getQuantity()).append("\n");
    total += item.getItem().getPrice() * item.getQuantity();
    }

    receiptBuilder.append("------------------------\n");
    receiptBuilder.append("Total: Php").append(total).append("\n");
    receiptBuilder.append("**Thank you for shopping!**\n"); 
    
    try {
        // Specify the file path relative to your project's root directory
        String filePath = "receipts/" + filename;
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        // Create the parent directory if it doesn't exist
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        FileWriter writer = new FileWriter(file);
        writer.write(receiptBuilder.toString());
        writer.close();
        System.out.println("Receipt saved to " + filePath);
    } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getMessage());
    }

    return receiptBuilder.toString(); // returning the virtual receipt
    
  }
    private void simulateOrderProcessing() {
    System.out.println("Processing order...");
    try {
        Thread.sleep(2000); // Simulate processing time
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    System.out.println("Order in transit...");
    try {
        Thread.sleep(5000); // Simulate transit time
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    System.out.println("Order arrived!");
    try {
        Thread.sleep(1000); // Simulate delivery time
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    System.out.println("Order received!");
    }
    private Voucher validateVoucher(String code) {
        for (Voucher voucher : voucherDatabase.getVouchers()) {
            if (voucher.getCode().equals(code)) {
                return voucher;
            }
        }
        return null;
    }


// Helper method to calculate total with discount
private double calculateTotalWithDiscount(double discount) {
  double total = 0;
  for (CartItem item : items) {
    total += item.getItem().getPrice() * item.getQuantity();
  }
  return total * (1 - discount);
        }
    }
 
class CartItem {
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
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class UserDatabase {
    private List<User> users;

    public UserDatabase() {
        users = new ArrayList();
        //Add initial users
        users.add(new User("admin", "password"));
        users.add(new User("kyle", "password"));
    }

    public boolean authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
      public void addUser(String username, String password) {
        users.add(new User(username, password));
    }
}
 class Voucher {
  private String code;
  private double discount;

  public Voucher(String code, double discount) {
    this.code = code;
    this.discount = discount;
  }

  // Getters for code and discount
  public String getCode() {
    return code;
  }

  public double getDiscount() {
    return discount;
  }
}

class VoucherDatabase {
  private List<Voucher> codes;

  public VoucherDatabase() {
    codes = new ArrayList<>();
    codes.add(new Voucher("Pwd2024", 0.1)); // 10% discount
    
    }
    public List<Voucher> getVouchers() {
        return codes;
  }
}

public class OnlineMarket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDatabase userDatabase = new UserDatabase();
        VoucherDatabase voucherDatabase = new VoucherDatabase();
        

        System.out.println("Welcome to STI Shopping!");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
            System.out.print("Enter username: ");
            String username = scanner.next();
            System.out.print("Enter password: ");
            String password = scanner.next();

        if(userDatabase.authenticate(username, password)) {
            System.out.println("Login successful!");
 
        ShoppingCart cart = new ShoppingCart(voucherDatabase);
        runShoppingCart(cart, scanner);
        } else {
                    System.out.println("Invalid username or password.");
                }
                break;  
        case 2:
                 //Registration Functionality
                System.out.print("Enter username: ");
                String newUsername = scanner.next();
                System.out.print("Enter password: ");
                String newPassword = scanner.next();
                
                userDatabase.addUser(newUsername, newPassword);
                System.out.println("Registration successful!");
                if (userDatabase.authenticate(newUsername, newPassword)) {
                    System.out.println("You are now logged in.");
                    ShoppingCart cart = new ShoppingCart(voucherDatabase);
                    runShoppingCart(cart, scanner);
                    } else {
                    System.out.println("Error logging in after registration.");
                }
                break;
        default:
                System.out.println("Invalid choice.");
                break;
                 }

        scanner.close();
    }
        private static void runShoppingCart(ShoppingCart cart, Scanner scanner) {        
        while (true) {
            System.out.println("\nMain Menu");
            System.out.println("0. Exit");
            System.out.println("1. Shop");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            System.out.print("Enter your choice: ");
            int mainChoice = scanner.nextInt();
            scanner.nextLine();
            System.out.println(" ");
 
            switch (mainChoice) {
                case 1:
                    while (true) {
                        System.out.println("Choose a collection");
                        System.out.println("0. Back to Main Menu");
                        System.out.println("1. Appliances");
                        System.out.println("2. Vegetables");
                        System.out.println("3. Meats");
                        System.out.println("4. Fruits");
                        System.out.print("Enter your choice: ");
                        int collectionChoice = scanner.nextInt();
                        System.out.println(" ");
 
                        if (collectionChoice == 0) {
                            break;
                        }
 
                        Market selectedMarket = null;
                        switch (collectionChoice) {
                            case 1:
                                selectedMarket = new Market(Arrays.asList(
                                        new Appliance("50inch TV with 2 Speaker", 47000), new Appliance("Samsung Smart Refrigerator with Electric Kettle", 25000)));
                                break;
                            case 2:
                                selectedMarket = new Market(Arrays.asList(
                                        new Vegetable("Tomato3kg", 100), new Vegetable("Potato3kg", 200)
                                ));
                                break;
                            case 3:
                                selectedMarket = new Market(Arrays.asList(
                                        new Meat("Chicken3kg", 700), new Meat("Beef3kg", 900)
                                ));
                                break;
                            case 4:
                                selectedMarket = new Market(Arrays.asList(
                                        new Fruit("Mango3kg", 450), new Fruit("Strawberry3kg", 800)
                                ));
                                break;
                            default:
                                System.out.println("Invalid choice.");
                                continue;
                        }
                        boolean goBackToMainMenu = false;
                        while (true) {
                        selectedMarket.displayItems();
                        System.out.println("\n8. Back to Collection List");
                        System.out.println("9. Back to Main Menu");
                        System.out.print("Enter the number of the item you want to buy: ");
                        int itemChoice = scanner.nextInt();
                        System.out.println(" ");
                        if (itemChoice == 8) {
                            break;
                        } else if (itemChoice == 9) {
                            goBackToMainMenu = true;
                            break;  //Go back to main menu
                            }        
                        itemChoice--;
                        MarketItem selectedItem = selectedMarket.getItem(itemChoice);
                        if (selectedItem != null) {
                            System.out.println("You selected: " + selectedItem.getName());
 
                            System.out.print("Enter the quantity: ");
                            int quantity = scanner.nextInt();
                            System.out.println(" ");
 
                            cart.addItem(selectedItem, quantity);
                            System.out.println("Item added to cart.");
                            System.out.println(" ");
                        } else {
                            System.out.println("Invalid item choice.");
                            System.out.println(" ");
                            }
                        }
                        if (goBackToMainMenu) {
                            break;  //Go back to main menu
                        }
                    }
                    break;
                case 2:
                    cart.displayCart();
                    break;
                case 3:
                    cart.displayCart();
                    cart.placeOrder(scanner);
                    break;
                case 0:
                    System.out.println("Thank you for shopping!");
                    return;
                default:
                    System.out.println("Invalid choice.");
                    } 
                }
            }               
}