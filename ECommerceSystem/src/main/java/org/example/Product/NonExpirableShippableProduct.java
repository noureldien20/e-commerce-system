package org.example.Product;

import org.example.Shippable;

public class NonExpirableShippableProduct extends Product implements Shippable {
    private final double weight;

    public NonExpirableShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override public boolean isExpired() {
        return false;
    }

    @Override public boolean needsShipping() {
        return true;
    }

    @Override public double getWeight() {
        return weight;
    }
}
