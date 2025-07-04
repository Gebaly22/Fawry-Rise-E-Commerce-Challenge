package ecommerce;

public class Cheese extends ExpirableProduct implements Shippable{
	private double weight; 
    private boolean expired;
	public Cheese(String name, double price, int quantity, double weight, boolean expired) {
		super(name, price, quantity);
		this.weight = weight;
		this.expired = expired;
	}
	 @Override
	 public String getName() {
	      return name;
	 }

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public boolean isExpired() {
		return expired;
	}

	@Override
	public boolean requiresShipping() {
		return true;
	}

}
