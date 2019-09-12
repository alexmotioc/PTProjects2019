package model;

public class Product {
	private int idProduct;
	private String name;
	private int price;
	private int stock;
	private String category;
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
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
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Product(int idProduct, String name, int price, int stock, String category) {
		super();
		this.idProduct = idProduct;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.category = category;
	}
	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", name=" + name + ", price=" + price + ", stock=" + stock
				+ ", category=" + category + "]";
	}
	
	

}
