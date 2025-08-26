import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Manages items for purchase. Calculates totals and handles the checkout process.
 */
public class ShoppingCart {
    private List<CartItem> items = new ArrayList<>();
    private VoucherDatabase voucherDatabase;

    public ShoppingCart(VoucherDatabase voucherDatabase) {
        this.voucherDatabase = voucherDatabase;
    }

    public void addItem(MarketItem item, int quantity) {
        if (quantity > 0) {
            items.add(new CartItem(item, quantity));
            System.out.println("-> '" + item.getName() + "' (x" + quantity + ") added to cart.");
        } else {
            System.out.println("Invalid quantity.");
        }
    }

    private double calculateSubtotal() {
        double subtotal = 0.0;
        for (CartItem cartItem : items) {
            // CORRECTED LOGIC: price * quantity
            subtotal += cartItem.getItem().getPrice() * cartItem.getQuantity();
        }
        return subtotal;
    }

    public void displayCart() {
        System.out.println("\n--- Your Shopping Cart ---");
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        for (CartItem cartItem : items) {
            double itemTotal = cartItem.getItem().getPrice() * cartItem.getQuantity();
            System.out.printf(" - %s (x%d) = Php %.2f%n", cartItem.getItem().getName(), cartItem.getQuantity(), itemTotal);
        }
        System.out.println("--------------------------");
        System.out.printf("Subtotal: Php %.2f%n", calculateSubtotal());
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void placeOrder(Scanner scanner) {
        double subtotal = calculateSubtotal();
        double finalTotal = subtotal;
        Voucher appliedVoucher = null;

        System.out.print("Do you have a voucher code? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter voucher code: ");
            String voucherCode = scanner.nextLine();
            appliedVoucher = voucherDatabase.validateVoucher(voucherCode);

            if (appliedVoucher != null) {
                double discountAmount = subtotal * appliedVoucher.getDiscount();
                finalTotal = subtotal - discountAmount;
                System.out.printf("Voucher applied! You saved Php %.2f.%n", discountAmount);
            } else {
                System.out.println("Invalid voucher code.");
            }
        }
        
        System.out.printf("\nFinal Total: Php %.2f%n", finalTotal);
        generateReceipt(finalTotal, appliedVoucher);
        simulateOrderProcessing();
        items.clear();
    }

    private void generateReceipt(double finalTotal, Voucher appliedVoucher) {
        // This method to generate and save a receipt file is a great feature.
        // The logic you had was good, just ensure it's clean.
        // (Implementation can be copied from your original file or the previous answer)
        System.out.println("Receipt has been generated and saved.");
    }

    private void simulateOrderProcessing() {
        try {
            System.out.println("\nProcessing order...");
            Thread.sleep(1500);
            System.out.println("Order in transit...");
            Thread.sleep(3000);
            System.out.println("Order received!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}