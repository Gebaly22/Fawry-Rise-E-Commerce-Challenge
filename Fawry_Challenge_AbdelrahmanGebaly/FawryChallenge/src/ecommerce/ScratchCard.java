package ecommerce;

public class ScratchCard extends NonExpirableProduct {

    public ScratchCard(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public boolean requiresShipping() {
        return false;
    }

    // No need to implement Shippable
}
