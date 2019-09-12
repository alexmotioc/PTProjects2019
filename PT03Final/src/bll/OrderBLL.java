package bll;

import dao.OrderDAO;
import dao.ProductDAO;
import model.Orders;
import model.Product;

public class OrderBLL {
	
	public void insertOrder(Orders order) {
		OrderDAO.insert(order);
	}
	
}
