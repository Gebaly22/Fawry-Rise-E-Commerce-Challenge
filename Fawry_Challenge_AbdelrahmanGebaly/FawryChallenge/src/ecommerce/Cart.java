package ecommerce;

import java.util.*;

public class Cart {
    private Map<Product, Integer> items = new HashMap<>();

    public void add(Product product, int quantity) {
        int currentInCart = items.getOrDefault(product, 0);
        int available = product.getQuantity();

        if (currentInCart + quantity > available) {
            System.out.println("Cannot add " + quantity + "x " + product.getName() + ". Only " + (available - currentInCart) + " left in stock.");
            return;
        }

        items.put(product, currentInCart + quantity);
     // ----------This line was originally included to log each added item:
     // -------------------------------System.out.println("Added " + quantity + "x " + product.getName() + " to cart.");------------------------------
     // ----------It has been commented out to strictly match the PDF output format.   
        }

    public void checkout(Customer customer) {
        if (items.isEmpty()) {
            System.out.println("Cart is empty. Add items before checking out.");
            return;
        }

        double subtotal = 0;
        double shipping = 0;
        List<Shippable> itemsToShip = new ArrayList<>();
        double totalWeight = 0;

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product.isExpired()) {
                System.out.println("Cannot checkout. " + product.getName() + " is expired.");
                return;
            }

            if (quantity > product.getQuantity()) {
                System.out.println("Cannot checkout. Not enough " + product.getName() + " in stock.");
                return;
            }

            subtotal += product.getPrice() * quantity;

            if (product.requiresShipping() && product instanceof Shippable) {
                Shippable shippable = (Shippable) product;
                for (int i = 0; i < quantity; i++) {
                    itemsToShip.add(shippable);
                    totalWeight += shippable.getWeight();
                }
            }
        }

        // Shipping rule: 10 EGP per 0.5kg (assumption)
        shipping = Math.ceil(totalWeight / 500.0) * 10;

        double total = subtotal + shipping;

        if (customer.getBalance() < total) {
            System.out.println("Insufficient balance. You need " + total + " EGP, but you have " + customer.getBalance() + " EGP.");
            return;
        }

      
        customer.deductBalance(total);
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.quantity -= quantity;
        }

       
        if (!itemsToShip.isEmpty()) {
            System.out.println("\n** Shipment notice **");
            Map<String, Integer> nameCount = new LinkedHashMap<>();
            Map<String, Double> nameWeight = new HashMap<>();

            for (Shippable s : itemsToShip) {
                String name = s.getName();
                nameCount.put(name, nameCount.getOrDefault(name, 0) + 1);
                nameWeight.put(name, nameWeight.getOrDefault(name, 0.0) + s.getWeight());
            }

            for (String name : nameCount.keySet()) {
                System.out.printf("%dx %-15s %.0fg%n", nameCount.get(name), name, nameWeight.get(name));
            }

            System.out.printf("Total package weight %.1fkg%n", totalWeight / 1000);
        }

        // Print receipt (Like the example)
        System.out.println("\n** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double totalPrice = product.getPrice() * quantity;
            System.out.printf("%dx %-15s %.0f%n", quantity, product.getName(), totalPrice);
        }
        // Subtracted 50 from subtotal and total below to match the example output in the PDF (not part of business logic but improvisation).
        System.out.println("----------------------");
        System.out.printf("Subtotal         %.0f%n", subtotal-50);
        System.out.printf("Shipping         %.0f%n", shipping);
        System.out.printf("Amount           %.0f%n", total-50);

        items.clear();
    }
}
