package BusinessLayer;

import java.io.Serializable;
import java.util.ArrayList;

public class CompositeProduct extends MenuItem{

	private ArrayList<MenuItem> ingredient;
	
	public CompositeProduct(int id, String name, int price, int weight) {
		super(id, name, price, weight);
		ingredient=new ArrayList<MenuItem>();
	}
	
	public void addIngredient(MenuItem menuItem) {
		ingredient.add(menuItem);
	}
	
	public void removeIngredient(MenuItem menuItem) {
		ingredient.remove(menuItem);
	}

	@Override
	public int computePrice() {
		int price=0;
		for(MenuItem i:ingredient) {
			price+=i.computePrice();
		}
		return price;
	}
	
	public int computeWeight() {
		int weight=0;
		for(MenuItem i:ingredient) {
			weight+=i.computeWeight();
		}
		return weight;
	}
	@Override
	public String toString() {
		String toReturn=new String();
		toReturn="CompositeProduct: "  + id + " " + name + " " + price + " " + weight;
		toReturn+="\nIngredients:\n";
		for(MenuItem i:ingredient) {
			toReturn+=i.toString() + "\n";
		}
		return toReturn;
	}
}
