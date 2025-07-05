package org.example.Cart;

import org.example.Shippable;
import org.example.Product.Product;
import java.util.*;

public class Cart {
    private final List<Item> items = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Not enough stock.");
        }
        items.add(new Item(product, quantity));
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<Item> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(Item::getTotalPrice).sum();
    }

    public List<Shippable> getShippables() {
        List<Shippable> list = new ArrayList<>();
        for (Item item : items) {
            if (item.getProduct() instanceof Shippable) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    list.add((Shippable) item.getProduct());
                }
            }
        }
        return list;
    }
}
