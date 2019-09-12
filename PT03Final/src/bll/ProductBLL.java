package bll;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import dao.ClientDAO;
import dao.ProductDAO;
import model.Client;
import model.Product;

public class ProductBLL {
	

	public ProductBLL() {
	}

	public ArrayList<Product> findProducts() {
		ArrayList<Product> st = ProductDAO.find();
		
		return st;
	}
	
	public void insertProduct(Product product) {
		ProductDAO.insert(product);
	}
	
	public void deleteProduct(int id) {
		ProductDAO.deleteById(id);
	}

	public Product findByID(int id) {
		return ProductDAO.findById(id);
	}
}
