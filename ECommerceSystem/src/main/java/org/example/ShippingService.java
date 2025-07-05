package org.example;

import java.util.List;

public class ShippingService {
    public static void shipItems(List<Shippable> items) {
        System.out.println("Shipping " + items.size() + " items:");
        for (Shippable s : items) {
            System.out.println("- " + s.getName() + " (Weight: " + s.getWeight() + ")");
        }
    }
}