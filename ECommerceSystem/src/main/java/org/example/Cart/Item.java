package org.example.Cart;

import org.example.Product.Product;

public class Item {
    private final Product product;
    private final int quantity;

    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public boolean needsShipping() {
        return product.needsShipping();
    }

    public void reduceStock() {
        product.reduceQuantity(this.quantity);
    }

    public boolean isExpired() {
        return product.isExpired();
    }

    public String getName() {
        return product.getName();
    }

    public int getQuantity() {
        return this.quantity;
    }

    public Product getProduct() {
        return this.product;
    }
}
