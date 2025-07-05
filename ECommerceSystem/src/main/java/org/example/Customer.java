package org.example;

import org.example.Cart.*;
import org.example.Product.Product;

public class Customer {
    private final String name;
    private double balance;
    private final Cart cart = new Cart();

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public Cart getCart() {
        return cart;
    }

    public void addToCart(Product product, int qty) {
        cart.addItem(product, qty);
    }

    public void checkout() {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty.");
        }

        double subtotal = cart.getSubtotal();
        for (Item item : cart.getItems()) {
            if (item.isExpired()) {
                throw new IllegalStateException("Product " + item.getName() + " is expired.");
            }
        }

        double shipping = cart.getShippables().stream().mapToDouble(Shippable::getWeight).sum() * 5;
        double total = subtotal + shipping;

        if (balance < total) {
            throw new IllegalStateException("Insufficient balance.");
        }

        balance -= total;

        for (Item item : cart.getItems()) {
            item.reduceStock();
        }

        System.out.println("=====| CHECKOUT SUMMARY |=====");
        System.out.println("Subtotal: $" + String.format("%.2f", subtotal));
        System.out.println("Shipping: $" + String.format("%.2f", shipping));
        System.out.println("Total Paid: $" + String.format("%.2f", total));
        System.out.println("Remaining Balance: $" + String.format("%.2f", balance));

        var toShip = cart.getShippables();
        if (!toShip.isEmpty()) {
            ShippingService.shipItems(toShip);
        }

        cart.clear();
    }
}
