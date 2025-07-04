package ecommerce;

import java.util.*;

public class ShippingService {
	
	public static void ship(List<Shippable> items) {
        if (items == null || items.isEmpty()) {
            System.out.println("No shippable items found.");
            return;
        }

        System.out.println("\n** Shipment notice **");

        // Count how many of each item, and total their weights
        Map<String, Integer> quantityMap = new LinkedHashMap<>();
        Map<String, Double> weightMap = new HashMap<>();
        double totalWeight = 0;

        for (Shippable item : items) {
            String name = item.getName();
            double weight = item.getWeight();

            quantityMap.put(name, quantityMap.getOrDefault(name, 0) + 1);
            weightMap.put(name, weightMap.getOrDefault(name, 0.0) + weight);
            totalWeight += weight;
        }

        // Prints the shippable items
        for (String name : quantityMap.keySet()) {
            int quantity = quantityMap.get(name);
            double weight = weightMap.get(name);
            System.out.printf("%dx %-15s %.0fg%n", quantity, name, weight);
        }
        
        System.out.printf("Total package weight %.1fkg%n", totalWeight / 1000);
    }
}

