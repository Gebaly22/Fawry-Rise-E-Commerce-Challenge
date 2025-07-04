package ecommerce;

public abstract class ExpirableProduct extends Product{

	public ExpirableProduct(String name, double price, int quantity) {
		super(name, price, quantity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract boolean isExpired(); 

	@Override
	public abstract boolean requiresShipping();

	

}
