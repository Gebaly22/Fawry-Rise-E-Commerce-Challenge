package ecommerce;

public class Main {
    public static void main(String[] args) {
        // Step 1: Create Products
        Product cheese = new Cheese("Cheese", 100, 5, 200, false); // price: 100, 200g
        Product biscuits = new Biscuits("Biscuits", 150, 3, 700, false);
        Product tv = new TV("TV", 5000, 2, 8000); // 8kg
        Product scratchCard = new ScratchCard("ScratchCard", 50, 10);

        // Step 2: Create Customer
        Customer customer = new Customer("Ali", 500);

        // Step 3: Create Cart and add items
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 1);  // Won’t appear in shipment, but shows in receipt

        // Step 4: Checkout
        cart.checkout(customer);
    }
}
