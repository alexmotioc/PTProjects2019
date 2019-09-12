package model;

public class Orders {
	private int idClient;
	private int idProduct;
	private int Quantity;
	public Orders(int idClient, int idProduct, int quantity) {
		super();
		this.idClient = idClient;
		this.idProduct = idProduct;
		Quantity = quantity;
	}
	
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	
}
