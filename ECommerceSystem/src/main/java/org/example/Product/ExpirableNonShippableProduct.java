package org.example.Product;

import java.time.LocalDate;

public class ExpirableNonShippableProduct extends Product {
    private final LocalDate expiryDate;

    public ExpirableNonShippableProduct(String name, double price, int quantity, LocalDate expiryDate) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
    }

    @Override public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    @Override public boolean needsShipping() {
        return false;
    }
}
