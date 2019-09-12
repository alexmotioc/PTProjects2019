package BusinessLayer;

import java.util.ArrayList;
import java.util.HashMap;

import DataLayer.FileWriter;
import DataLayer.RestaurantSerializator;

public class Restaurant implements IRestaurantProcessing{
	private ArrayList<MenuItem> products;
	private HashMap<Order,ArrayList<MenuItem>> orders;
	RestaurantSerializator serializator=new RestaurantSerializator();
	public Restaurant() {
		if(serializator.Deserialize()!=null)
			products=serializator.Deserialize(); //se iau datele din fisier
		else
			products=new ArrayList<MenuItem>();
		orders=new HashMap<Order,ArrayList<MenuItem>>();
	}
	
	@Override
	public void createMenuItem(MenuItem menuItem) {
		assert wellFormed():"Is not well formed";
		assert menuItem!=null:"Item is null";
		products.add(menuItem);
		serializator.Serialize(products);
		assert products.contains(menuItem):"Didn't add the item";
	}
	@Override
	public void deleteMenuItem(int id) {
		assert wellFormed():"Is not well formed";
		boolean exists=false;
		for(MenuItem i:products) {
			if(i.getId()==id) {
				products.remove(i);
				exists=true;
				break;
			}
		}
		assert exists:"Could not found item with id";
		exists=false;
		for(MenuItem i:products) {
			if(i.getId()==id) {
				exists=true;
				break;
			}
		}
		assert !exists:"Delete failed";
		serializator.Serialize(products);
	}
	@Override
	public void editMenuItem(MenuItem menuItem) {
		assert wellFormed():"Is not wellFormed";
		boolean exists=false;
		assert menuItem!=null:"MenuItem is null";
		for(MenuItem i:products) {
			if(i.getId()==menuItem.getId()) {
				products.remove(i);
				exists=true;
				break;
			}
		}
		assert exists:"Could not found item with id";
		products.add(menuItem);
		serializator.Serialize(products);
	}
	
	public MenuItem getProductById(int id) {
		for(MenuItem i:products) {
			if(i.getId()==id)
				return i;
		}
		return null;
	}
	@Override
	public void createOrder(Order order,ArrayList<MenuItem> items) {
		assert wellFormed():"Is not wellFormed";
		assert order!=null:"Order is null";
		assert items!=null:"ArrayList for items is null";
		orders.put(order, items);
		assert orders.containsKey(order):"Creating order not succesful";
	}
	@Override
	public int computePriceForOrder(Order order) {
		assert wellFormed():"Is not wellFormed";
		assert order!=null:"Order is null";
		assert orders.containsKey(order):"Order was not found";
		int price=0;
		for(MenuItem i:orders.get(order)) {
			price+=i.computePrice();
		}
		assert price>0:"Price is negative";
		return price;
	}
	@Override
	public void generateBill(Order order) {
		assert wellFormed():"Is not wellFormed";
		assert order!=null:"Order is null";
		assert orders.containsKey(order);
		FileWriter fw=new FileWriter();
		fw.addLine("Order:");
		fw.addLine(order.toString());
		fw.addLine("Items:");
		for(MenuItem i:orders.get(order)) {
			fw.addLine(i.toString());
		}
		fw.addLine("Total: " + computePriceForOrder(order));
		fw.write();
	}

	public ArrayList<MenuItem> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<MenuItem> products) {
		this.products = products;
	}

	public HashMap<Order, ArrayList<MenuItem>> getOrders() {
		return orders;
	}

	public void setOrders(HashMap<Order, ArrayList<MenuItem>> orders) {
		this.orders = orders;
	}
	
	private boolean wellFormed() {
		if(products==null || orders==null)
			return false;
		return true;
	}

}
