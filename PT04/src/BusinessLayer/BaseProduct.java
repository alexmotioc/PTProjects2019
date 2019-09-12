package BusinessLayer;

import java.io.Serializable;

public class BaseProduct extends MenuItem{

	public BaseProduct(int id, String name, int price, int weight) {
		super(id, name, price, weight);
	}

	@Override
	public int computePrice() {
		return price;
	}

	@Override
	public int computeWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return "BaseProduct: "  + id + " " + name + " " + price + " " + weight;
	}
	
	

}
