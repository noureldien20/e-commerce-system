package org.example.Product;

public abstract class Product {
    protected String name;
    protected double price;
    protected int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void reduceQuantity(int qty) {
        if (qty > quantity) throw new IllegalArgumentException("Not enough stock.");
        quantity -= qty;
    }

    public abstract boolean isExpired();
    public abstract boolean needsShipping();
}