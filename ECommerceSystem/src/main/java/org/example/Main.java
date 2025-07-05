package org.example;

import org.example.Product.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== E-COMMERCE SYSTEM TESTING ===\n");

        Product cheese = new ExpirableShippableProduct("Cheese", 10.0, 5, LocalDate.now().plusDays(3), 1.5);
        Product tv = new NonExpirableShippableProduct("TV", 500.0, 10, 10.0);
        Product mobileCard = new NonExpirableNonShippableProduct("Mobile Card", 5.0, 10);
        Product expiredMilk = new ExpirableShippableProduct("Expired Milk", 3.0, 5, LocalDate.now().minusDays(1), 1.0);
        Product laptop = new NonExpirableShippableProduct("Laptop", 800.0, 3, 2.5);
        Product giftCard = new NonExpirableNonShippableProduct("Gift Card", 25.0, 20);
        Product expiredMedicine = new ExpirableNonShippableProduct("Expired Medicine", 15.0, 10, LocalDate.now().minusDays(2));

        System.out.println("--- Test 1: Successful checkout with mixed products ---");
        testSuccessfulCheckout(cheese, tv, mobileCard);

        System.out.println("\n--- Test 2: Checkout with only non-shippable items ---");
        testNonShippableOnly(mobileCard, giftCard);

        System.out.println("\n--- Test 3: Checkout with only shippable items ---");
        testShippableOnly(cheese, tv);

        System.out.println("\n--- Test 4: Expired shippable product should fail ---");
        testExpiredProduct(cheese, expiredMilk);

        System.out.println("\n--- Test 5: Expired non-shippable product should fail ---");
        testExpiredNonShippable(mobileCard, expiredMedicine);

        System.out.println("\n--- Test 6: Insufficient balance should fail ---");
        testInsufficientBalance(tv);

        System.out.println("\n--- Test 7: Empty cart should fail ---");
        testEmptyCart();

        System.out.println("\n--- Test 8: Insufficient stock should fail ---");
        testInsufficientStock(cheese);

        System.out.println("\n--- Test 9: Invalid quantity should fail ---");
        testInvalidQuantity(cheese);

        System.out.println("\n--- Test 10: Complex scenario with multiple products ---");
        testComplexScenario(cheese, tv, mobileCard, giftCard);

        System.out.println("\n--- Test 11: Edge case - exact balance scenarios ---");
        testExactBalance(tv);

    }

    private static void testSuccessfulCheckout(Product cheese, Product tv, Product mobileCard) {
        Customer customer = new Customer("John Doe", 1000.0);
        System.out.println("Customer: " + customer.getName() + " | Balance: $" + customer.getBalance());

        customer.addToCart(cheese, 2);
        customer.addToCart(tv, 1);
        customer.addToCart(mobileCard, 1);

        System.out.println("Added to cart: 2x Cheese, 1x TV, 1x Mobile Card");
        customer.checkout();
    }

    private static void testNonShippableOnly(Product mobileCard, Product giftCard) {
        Customer customer = new Customer("Alice Smith", 100.0);
        System.out.println("Customer: " + customer.getName() + " | Balance: $" + customer.getBalance());

        customer.addToCart(mobileCard, 2);
        customer.addToCart(giftCard, 1);

        System.out.println("Added to cart: 2x Mobile Card, 1x Gift Card (no shipping expected)");
        customer.checkout();
    }

    private static void testShippableOnly(Product cheese, Product tv) {
        Customer customer = new Customer("Bob Johnson", 600.0);
        System.out.println("Customer: " + customer.getName() + " | Balance: $" + customer.getBalance());

        customer.addToCart(cheese, 1);
        customer.addToCart(tv, 1);

        System.out.println("Added to cart: 1x Cheese, 1x TV (shipping expected)");
        customer.checkout();
    }

    private static void testExpiredProduct(Product cheese, Product expiredMilk) {
        Customer customer = new Customer("Carol Williams", 500.0);
        System.out.println("Customer: " + customer.getName() + " | Balance: $" + customer.getBalance());

        try {
            customer.addToCart(cheese, 1);
            customer.addToCart(expiredMilk, 1);

            System.out.println("Added to cart: 1x Cheese, 1x Expired Milk");
            customer.checkout();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void testExpiredNonShippable(Product mobileCard, Product expiredMedicine) {
        Customer customer = new Customer("Dave Brown", 100.0);
        System.out.println("Customer: " + customer.getName() + " | Balance: $" + customer.getBalance());

        try {
            customer.addToCart(mobileCard, 1);
            customer.addToCart(expiredMedicine, 1);

            System.out.println("Added to cart: 1x Mobile Card, 1x Expired Medicine");
            customer.checkout();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void testInsufficientBalance(Product tv) {
        Customer customer = new Customer("Poor Pete", 10.0);
        System.out.println("Customer: " + customer.getName() + " | Balance: $" + customer.getBalance());

        try {
            customer.addToCart(tv, 1);

            System.out.println("Added to cart: 1x TV (costs $500 + shipping)");
            customer.checkout();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void testEmptyCart() {
        Customer customer = new Customer("Empty Eddie", 1000.0);
        System.out.println("Customer: " + customer.getName() + " | Balance: $" + customer.getBalance());

        try {
            System.out.println("Attempting checkout with empty cart");
            customer.checkout();
        } catch (Exception e) {
            System.out.println("✓ Test PASSED: Correctly detected empty cart - " + e.getMessage());
        }
    }

    private static void testInsufficientStock(Product cheese) {
        Customer customer = new Customer("Greedy Gary", 1000.0);
        System.out.println("Customer: " + customer.getName() + " | Balance: $" + customer.getBalance());

        try {
            System.out.println("Attempting to add 10x Cheese (only 5 available)");
            customer.addToCart(cheese, 10);
        } catch (Exception e) {
            System.out.println("✓ Test PASSED: Correctly detected insufficient stock - " + e.getMessage());
        }
    }

    private static void testInvalidQuantity(Product cheese) {
        Customer customer = new Customer("Invalid Ian", 1000.0);
        System.out.println("Customer: " + customer.getName() + " | Balance: $" + customer.getBalance());

        try {
            System.out.println("Attempting to add 0x Cheese (invalid quantity)");
            customer.addToCart(cheese, 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Attempting to add -1x Cheese (negative quantity)");
            customer.addToCart(cheese, -1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void testComplexScenario(Product cheese, Product tv, Product mobileCard, Product giftCard) {
        Customer customer = new Customer("Complex Carl", 1000.0);
        System.out.println("Customer: " + customer.getName() + " | Balance: $" + customer.getBalance());

        customer.addToCart(cheese, 2);      // Expirable + Shippable
        customer.addToCart(tv, 1);          // Non-expirable + Shippable
        customer.addToCart(mobileCard, 3);  // Non-expirable + Non-shippable
        customer.addToCart(giftCard, 2);    // Non-expirable + Non-shippable

        System.out.println("Added to cart: 2x Cheese, 1x TV, 3x Mobile Card, 2x Gift Card");
        System.out.println("Expected: Subtotal = $585, Shipping = $65, Total = $650");
        customer.checkout();

    }

    private static void testExactBalance(Product tv) {
        Customer customer1 = new Customer("Almost Rich", 549.0);
        System.out.println("Customer: " + customer1.getName() + " | Balance: $" + customer1.getBalance());

        try {
            customer1.addToCart(tv, 1);
            System.out.println("Added 1x TV (needs $550 total)");
            customer1.checkout();
        } catch (Exception e) {
            System.out.println("✓ Test PASSED: Correctly detected insufficient balance - " + e.getMessage());
        }

        Customer customer2 = new Customer("Exact Rich", 550.0);
        System.out.println("Customer: " + customer2.getName() + " | Balance: $" + customer2.getBalance());

        customer2.addToCart(tv, 1);
        System.out.println("Added 1x TV (exactly $550 total)");
        customer2.checkout();
    }
}
