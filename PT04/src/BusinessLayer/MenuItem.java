package BusinessLayer;

import java.io.Serializable;

public abstract class MenuItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int id;
	protected String name;
	protected int price;
	protected int weight;
	public MenuItem(int id, String name, int price,int weight) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.weight=weight;
	}
	
	public abstract int computePrice();
	public abstract int computeWeight();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
}
