package org.example.Product;

public class NonExpirableNonShippableProduct extends Product {
    public NonExpirableNonShippableProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override public boolean isExpired() {
        return false;
    }

    @Override public boolean needsShipping() {
        return false;
    }
}
