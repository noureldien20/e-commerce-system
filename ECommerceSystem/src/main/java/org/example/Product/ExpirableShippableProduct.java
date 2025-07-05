package org.example.Product;

import org.example.Shippable;
import java.time.LocalDate;

public class ExpirableShippableProduct extends Product implements Shippable {
    private final LocalDate expiryDate;
    private final double weight;

    public ExpirableShippableProduct(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
        this.weight = weight;
    }

    @Override public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    @Override public boolean needsShipping() {
        return true;
    }

    @Override public double getWeight() {
        return weight;
    }
}
